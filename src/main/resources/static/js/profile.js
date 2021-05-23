$(document).ready(function () {
    $('#profile-image').attr("src","http://localhost:8080/files");
});

function sendFile() {
    $('#profile-image').attr("src","");

    // данные для отправки
    let formData = new FormData();
    // забрал файл из input
    let files = ($('#file'))[0]['files'];
    // добавляю файл в formData
    [].forEach.call(files, function (file, i, files) {
        formData.append("file", file);
    });

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/files",
        data: formData,
        processData: false,
        contentType: false
    }).done(function (response) {
        location.reload();
    }).fail(function () {
        alert('Ошибка при загрузке файла')
    });
}

function addPagination(url) {
    $('#pagination').twbsPagination({
        totalPages: pageCount,
        visiblePages: 4,
        next: 'Следуюшая страница',
        prev: 'Предыдущая страница',
        last: 'Последняя страница',
        first: 'Начальная страница',
        onPageClick: function(event, page) {

            clearColumns();

            $.ajax({
                url: url + "?page=" + (page-1),
                context: document.body,
            }).done(function (msg) {
                let values = msg['value'];
                for (let i = 0; i < 10; i++) {
                    $("#column-1").append(generateDiv(values, i));
                }
                for (let i = 10; i < 20; i++) {
                    $("#column-2").append(generateDiv(values, i));
                }
                for (let i = 20; i < 30; i++) {
                    $("#column-3").append(generateDiv(values, i));
                }
                for (let i = 30; i < 40; i++) {
                    $("#column-4").append(generateDiv(values, i));
                }
            });
        }
    });
}