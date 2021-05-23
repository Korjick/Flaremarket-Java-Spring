const getQueryParameter = (param) => new URLSearchParams(window.location.search).get(param);

$( document ).ready(function() {
    registrationForm();
    if(getQueryParameter('acceptYourAccount') !== null) {
        alert("Пожалуйста подтвердите ваш аккаунт. Письмо было отправлено вам на электронную почту");
    }
});

function registrationForm() {
    $('#exampleInputPassword1, #exampleInputPasswordRepeat1').on('keyup', function () {
        if ($('#exampleInputPassword1').val() === $('#exampleInputPasswordRepeat1').val()) {
            $('#registerMessage').html('Passwords match').css('color', 'green');
            $("#registerButton").prop("disabled", false);
        } else {
            $('#registerMessage').html('Password mismatch').css('color', 'red');
            $("#registerButton").prop("disabled", true);
        }
    });
}