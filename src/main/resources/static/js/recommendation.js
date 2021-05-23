$(document).ready(function(){
    $('#adv').hover(function() {
        $("#adv").addClass('transition');
    }, function() {
        $("#adv").removeClass('transition');
    });

    getPlatforms();
});

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