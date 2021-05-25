let deal1, deal2, deal3;

$(document).ready(function(){
    $('#adv').hover(function() {
        $("#adv").addClass('transition');
    }, function() {
        $("#adv").removeClass('transition');
    });

    var filterHours = getURLParameter(window.location.href, 'filterHours');
    if(filterHours !== null) $("#marketDropdownMenuLink").text(filterHours + 'h.');

    editForm();
    dealHover();
    getPlatforms();
    getStats();
});

function getURLParameter(url, name) {
    return (RegExp(name + '=' + '(.+?)(&|$)').exec(url)||[,null])[1];
}

function getPlatforms(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/games/platforms",
    }).done(function (response) {
        for (let i = 0; i < response.length; i++) {
            $("#inputPlatform").append('<option value="' + response[i] + '" selected>' + response[i] + '</option>');
        }
    }).fail(function () {
        alert('Ошибка при загрузке платформ')
    });
}

function getStats(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/solds/lastweek",
    }).done(function (response) {
        let data = [];
        let labels = [];
        for(let i = 0; i < response.length; i++) {
            labels.push(response[i]['key'])
            data.push(response[i]['value']);
        }
        addStats(labels, data);
    }).fail(function () {
        alert('Ошибка при загрузке данных')
    });
}

function addStats(labels, data){
    var ctx = document.getElementById('stats').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Last Solds',
                data: data,
                backgroundColor: [
                    'rgb(75, 165, 130)',
                ],
                borderColor: [
                    'rgb(75, 165, 130)'
                ],
                borderWidth: 2
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function reloadDifferentFilterHour(item) {
    var val = $(item).val();
    window.location.href = "http://localhost:8080/recommendation?filterHours=" + val;
}

function dealHover(){

    deal1 = $("#deal1").text();
    deal2 = $("#deal2").text();
    deal3 = $("#deal3").text();

    $('#deal1').hover(function() {
        $("#deal1").text("Buy");
    }, function() {
        $("#deal1").text(deal1);
    });

    $('#deal2').hover(function() {
        $("#deal2").text("Buy");
    }, function() {
        $("#deal2").text(deal2);
    });

    $('#deal3').hover(function() {
        $("#deal3").text("Buy");
    }, function() {
        $("#deal3").text(deal3);
    });
}

function editForm() {
    $('#exampleInputPassword1, #exampleInputPassword2').on('keyup', function () {
        if ($('#exampleInputPassword1').val() === $('#exampleInputPassword2').val()) {
            $('#editMessage').html('Passwords match').css('color', 'green');
            $("#accept").prop("disabled", false);
        } else {
            $('#editMessage').html('Password mismatch').css('color', 'red');
            $("#accept").prop("disabled", true);
        }
    });
}

function getPhone(){

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    if($("#exampleInputNumber2").val() !== ''){
        confirmCode = parseInt(Math.random() * 9000 + 1000, 10);
        console.log(confirmCode);
        let data = {
            "phone": $("#exampleInputNumber2").val(),
            "text": confirmCode,
        }
        $.ajax({
            url: "http://localhost:8080/sms",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            processData: false,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            error: function(){
                alert("Ошибка при отправке запроса");
            },
            success: function () {
                $("#exampleInputCode2").prop("disabled", false);
                $("#editForm").append('<input type="hidden" name="confirmCode" value="' + confirmCode + '">');
                $("#accept").attr("type", "submit");
            }
        });
    }
}