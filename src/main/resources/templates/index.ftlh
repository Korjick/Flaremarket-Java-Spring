<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Flaremarket</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<@spring.url '/css/index.css'/>">
</head>
<body>
<div id="background">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="<@spring.url '/img/Logo.png'/>" alt="" width="350" height="75">
            </a>
            <div class="d-flex align-items-center">
                <a href="#">About</a>
                <a class="ms-3" href="#">Contact</a>
                <a type="button" class="ms-4" style="font-weight: bold;" data-bs-toggle="modal" data-bs-target="#loginModal">Login</a>
                <div id="separator"></div>
                <button type="button" class="d-flex justify-content-center align-items-center" id="register" data-bs-toggle="modal" data-bs-target="#registerModal">
                    <p style="margin-bottom: 0; font-weight: bold;">Register</p>
                </button>
            </div>
        </div>
    </nav>
    <!-- Modals -->
    <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Registration</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/signUp" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Email address</label>
                            <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputNickname1" class="form-label">Nickname</label>
                            <input type="text" name="nickname" class="form-control" id="exampleInputNickname1" required>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" id="exampleInputPassword1" required>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPasswordRepeat1" class="form-label">Password Repeat</label>
                            <input type="password" name="password_repeat" class="form-control" id="exampleInputPasswordRepeat1" required>
                        </div>
                        <p id="registerMessage"></p>
                        <div class="d-flex">
                            <button id="registerButton" type="submit" class="btn" style="color: white; background-color: #ff5e5e">Register</button>
                            <a class="ms-2 btn d-flex align-items-center justify-content-center" href="" style="background-color: #ff5e5e; width: 40px;"><img src="<@spring.url '/img/Google.png'/>" width="30" height="30" alt=""></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Authorization</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/signIn" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="mb-3">
                            <label for="exampleInputEmail2" class="form-label">Email address or Nickname</label>
                            <input type="text" name="email" class="form-control" id="exampleInputEmail2" aria-describedby="emailHelp" required>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword2" class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" id="exampleInputPassword2" required>
                        </div>
                        <div class="mb-3 form-check">
                            <input name="remember-me" type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Remember Me</label>
                        </div>
                        <div class="d-flex">
                            <button id="registerButton" type="submit" class="btn" style="color: white; background-color: #ff5e5e">Sign In</button>
                            <a class="ms-2 btn d-flex align-items-center justify-content-center" href="" style="background-color: #ff5e5e; width: 40px;"><img src="<@spring.url '/img/Google.png'/>" width="30" height="30" alt=""></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Modals -->
    <div class="ms-3 d-flex flex-column align-items-start justify-content-center" id="content">
        <h1>Fastest & secure platform to exchange game objects</h1>
        <p class="my-3">Buy and sell objects, trusted by 10M wallets with over $30 billion in transactions.</p>
        <button class="d-flex justify-content-center align-items-center" id="tryfree" data-bs-toggle="modal" data-bs-target="#registerModal">
            <p style="margin-bottom: 0rem; font-weight: bold;">Try For Free</p>
            <img class="ms-2" src="<@spring.url '/img/Arrow_Right.png'/>" alt="" width="30" height="30">
        </button>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="<@spring.url '/js/index.js'/>"></script>
</body>
</html>
