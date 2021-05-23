<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Thanks for registration</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@700&display=swap" rel="stylesheet">
    <style>
        h1 {
            font-family: 'Poppins', sans-serif;
            color: white;
            width: 571px;
            height: 480px;
            left: 93px;
            top: 195px;
            font-style: normal;
            font-weight: bold;
            font-size: 64px;
            line-height: 80px;
        }
        a {
            color: #614EF3;
        }
        #back {
            width: 1440px;
            height: 850px;
            background-image: url("https://i.imgur.com/JZskMHJ.png");
        }

        .relate {
            position: relative;
            padding-top: 150px;
            padding-left: 30px;
        }
    </style>
</head>
<body>
<div id="back">
    <div class="relate">
        <h1>
            We are glad to see you on our platform. To verify your account, <a href="http://localhost:8080/confirm/${confirm_code}">please follow the link</a>
        </h1>
    </div>
</div>
</body>
</html>