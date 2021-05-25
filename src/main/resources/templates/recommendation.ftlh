<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Recommendation</title>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<@spring.url '/css/recommendation.css'/>">
</head>
<body>
<div class="background d-flex">
    <div class="left-menu d-flex flex-column justify-content-between">
        <div class="mt-5 d-flex justify-content-center">
            <img src="<@spring.url '/img/Logo-mini.png'/>" alt="">
        </div>
        <div class="d-flex flex-column align-items-center">
            <a class="my-4" href="<@spring.url '/recommendation'/>"><img src="<@spring.url '/img/Recommendation.png'/>"
                                                                         width="40" height="40" alt=""></a>
            <a class="my-4" href="<@spring.url '/recommendation'/>"><img src="<@spring.url '/img/Bill-n.png'/>"
                                                                         width="40" height="40" alt=""></a>
            <a class="my-4" href="<@spring.url '/auction'/>"><img src="<@spring.url '/img/Bet-n.png'/>"
                                                                         width="40" height="40" alt=""></a>
            <a class="my-4" href="<@spring.url '/myitems'/>"><img src="<@spring.url '/img/Search-n.png'/>"
                                                                         width="40" height="40" alt=""></a>
        </div>
        <div class="mb-5 d-flex justify-content-center">
            <a class="my-4" href="<@spring.url '/logout'/>"><img src="<@spring.url '/img/Logout.png'/>" width="40"
                                                                 height="40" alt=""></a>
        </div>
    </div>
    <div class="main">
        <nav class="my-3 navbar navbar-dark">
            <div class="container-fluid">
                <a class="ms-5 navbar-brand">Overview</a>
                <div class="d-flex align-items-center">
                    <a class="me-3"  href="<@spring.url '/myitems'/>"><img src="<@spring.url '/img/SearchButton.png'/>"
                                                                         alt="" width="50" height="50"></a>
                    <div class="profile d-flex justify-content-center align-items-center">
                        <#if user.getProfileImage()??>
                            <a href=""><img class="profile-image" id="profile-image"
                                 src="<@spring.url '/files/${user.getProfileImage().getProfileImageUrl()}'/>" alt=""
                                 width="40" height="40"></a>
                        <#else>
                            <a href="#" data-bs-toggle="modal" data-bs-target="#imageModal"><img class="profile-image" src="<@spring.url '/img/Default.png'/>" alt="" width="40"
                                 height="40"></a>
                        </#if>
                        <!-- Modal -->
                        <div class="modal fade" id="imageModal" tabindex="-1" aria-labelledby="imageModal" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form enctype="multipart/form-data" action="<@spring.url '/profile/image'/>" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <div class="input-group mb-3">
                                                <input type="file" name="image" class="form-control" required id="inputGroupFile01">
                                            </div>
                                            <button type="submit" class="btn btn-primary">Update</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal -->
                        <div>
                            <a class="ms-2" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">${user.getNickname()}</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#sellModal"
                                       href="#">Sell Item</a></li>
                                <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#editProfileModal"
                                       href="#">Edit Profile</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row mx-5 mt-4 justify-content-between">
                <div class="col-5">
                    <h1>Favourite</h1>
                    <div class="mt-3">
                        <canvas id="stats" width="650" height="250"></canvas>
                    </div>
                </div>
                <div class="col-6">
                    <h1>Day Deal</h1>
                    <div class="mt-3 d-flex" id="daydeal">
                        <#if (deal?size gt 2)>
                            <div class="deal d-flex flex-column justify-content-center align-items-center ms-5">
                                <img src="<@spring.url '/files/${deal[0].image}'/>" width="160" height="110" alt="">
                                <p class="my-2">${deal[0].getDescription()}</p>
                                <form action="<@spring.url '/sold/buy'/>" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input type="hidden" name="itemId" value="${deal[0].id}">
                                    <button id="deal1" type="submit" class="btn btn-outline-success mb-3"
                                            style="color: green; background-color: white; width: 150px; height: 50px;">${deal[0].price}
                                        $
                                    </button>
                                </form>
                            </div>
                            <div class="deal d-flex flex-column justify-content-center align-items-center ms-5">
                                <img src="<@spring.url '/files/${deal[1].image}'/>" width="160" height="110" alt="">
                                <p class="my-2">${deal[1].getDescription()}</p>
                                <form action="<@spring.url '/sold/buy'/>" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input type="hidden" name="itemId" value="${deal[1].id}">
                                    <button id="deal2" type="submit" class="btn btn-outline-success mb-3"
                                            style="color: green; background-color: white; width: 150px; height: 50px;">${deal[1].price}
                                        $
                                    </button>
                                </form>
                            </div>
                            <div class="deal d-flex flex-column justify-content-center align-items-center ms-5">
                                <img src="<@spring.url '/files/${deal[2].image}'/>" width="160" height="110" alt="">
                                <p class="my-2">${deal[2].getDescription()}</p>
                                <form action="<@spring.url '/sold/buy'/>" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input type="hidden" name="itemId" value="${deal[2].id}">
                                    <button id="deal3" type="submit" class="btn btn-outline-success mb-3"
                                            style="color: green; background-color: white; width: 150px; height: 50px;">${deal[2].price}
                                        $
                                    </button>
                                </form>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="col-7 mt-5">
                    <div class="d-flex justify-content-between">
                        <h1>Market</h1>
                        <div class="market-filter">
                            <button id="marketDropdownMenuLink" style="border-radius: 40px;" type="button"
                                    class="btn btn-outline-light dropdown-toggle"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                24h.
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="marketDropdownMenuLink">
                                <li class="dropdown-item" onclick="reloadDifferentFilterHour(this)" value="24">24h.</li>
                                <li class="dropdown-item" onclick="reloadDifferentFilterHour(this)" value="12">12h.</li>
                                <li class="dropdown-item" onclick="reloadDifferentFilterHour(this)" value="8">8h.</li>
                            </ul>
                        </div>
                    </div>
                    <div class="row overflow-auto">
                        <div class="col-4">
                            <p>Name</p>
                            <div id="marketName" class="d-flex flex-column justify-content-center">
                                <#list market as m>
                                    <#if m.getUserId() != user.getId()>
                                        <button class="btn btn-outline-light mb-3" data-bs-toggle="modal"
                                                data-bs-target="#marketNameDescriptionImage">${m.description}</button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="marketNameDescriptionImage" tabindex="-1"
                                             aria-labelledby="marketNameDescriptionImage" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body d-flex align-items-center">
                                                        <img src="<@spring.url '/files/${m.image}'/>" width="320"
                                                             height="220" alt="">
                                                        <p class="ms-3">${m.description}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Modal -->
                                    </#if>
                                </#list>
                            </div>
                        </div>
                        <div class="col-2">
                            <p>Price</p>
                            <div id="marketPrice" class="d-flex flex-column justify-content-center">
                                <#list market as m>
                                    <#if m.getUserId() != user.getId()>
                                        <div class="btn btn-outline-success mb-3">${m.price}$</div>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                        <div class="col-2">
                            <p>Chanee</p>
                            <div id="marketChanee" class="d-flex flex-column justify-content-center">
                                <#list market as m>
                                    <#if m.getUserId() != user.getId()>
                                        <#if (m.getChanee() > 1)>
                                            <div class="btn btn-outline-danger mb-3">+${(m.getChanee() - 1) * 100}%
                                            </div>
                                        <#elseif (m.getChanee() < 1)>
                                            <div class="btn btn-outline-success mb-3">-${(1 - m.getChanee()) * 100}%
                                            </div>
                                        <#else>
                                            <div class="btn btn-outline-light mb-3">${m.getChanee() - 1}%</div>
                                        </#if>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                        <div class="col-2">
                            <p>User</p>
                            <div id="marketUser" class="d-flex flex-column justify-content-center">
                                <#list market as m>
                                    <#if m.getUserId() != user.getId()>
                                        <#list users as u>
                                            <#if m.userId == u.getId()>
                                                <div class="btn btn-outline-light mb-3">${u.getNickname()}</div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                        <div class="col-2">
                            <p>Buy</p>
                            <div id="marketBuy" class="d-flex flex-column justify-content-center">
                                <#list market as m>
                                    <#if m.getUserId() != user.getId()>
                                        <form action="<@spring.url '/sold/buy'/>" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <input type="hidden" name="itemId" value="${m.id}">
                                            <button type="submit" class="btn btn-outline-success mb-3">Buy</button>
                                        </form>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4 mt-5">
                    <a href="<@spring.url '/auction'/>">
                        <img id="adv" src="<@spring.url '/img/AuctionAdv.png'/>" alt="">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="sellModal" tabindex="-1" aria-labelledby="addItem" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New Item</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form enctype="multipart/form-data" action="<@spring.url '/sell'/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="inputImage">Image</label>
                        <input required type="file" name="file" class="form-control" id="inputCreateItemImage"
                               aria-label="Upload">
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="inputDescription">Description</label>
                        <input required type="text" name="description" class="form-control" id="inputDescription">
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="inputGame">Game</label>
                        <select required class="form-select" id="inputGame" name="game">
                            <option value="CSGO" selected>CSGO</option>
                            <option value="DOTA2">DOTA2</option>
                            <option value="OTHER">OTHER</option>
                        </select>
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="inputSellType">SellType</label>
                        <select required class="form-select" id="inputSellType" name="sellType">
                            <option value="PRIMARY" selected>PRIMARY</option>
                            <option value="AUCTION">AUCTION</option>
                        </select>
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="inputPlatform">Platform</label>
                        <select required class="form-select" id="inputPlatform" name="platform">
                            <option value="PC" selected>PC</option>
                            <option value="PS5">Playstation 5</option>
                            <option value="XBOXONE">Xbox One</option>
                        </select>
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="inputPrice">Price</label>
                        <input required type="number" id="inputPrice" name="price">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Create</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->

<!-- Modal -->
<div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex justify-content-center">
                <form class="my-3" action="<@spring.url '/profile/edit'/>" method="post" id="editForm">
                    <input type="hidden" id="email" name="email" value="${user.email}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="mb-3">
                        <label for="exampleInputNickname1" class="form-label">Имя пользователя</label>
                        <input type="text" aria-label="${user.nickname}"
                               class="form-control" id="exampleInputNickname1" aria-describedby="nicknameHelp"
                               name="nickname"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Новый пароль</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" name="password" required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword2" class="form-label">Повторите новый пароль</label>
                        <input type="password" class="form-control" id="exampleInputPassword2" name="password-repeat"
                               required>
                        <p id="message"></p>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputNumber2" class="form-label">Введите номер телефона</label>
                        <input type="tel" placeholder="880055535353" class="form-control" id="exampleInputNumber2"
                               name="number" required>
                        <button type="button" class="mt-3 btn btn-outline-success" onclick="getPhone()">Отправить
                        </button>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputCode2" class="form-label">Код из смс</label>
                        <input type="number" class="form-control" id="exampleInputCode2" name="code" disabled required>
                    </div>
                    <div class="my-3 w-100 d-flex justify-content-center">
                        <button type="button" class="btn btn-outline-success" id="accept">Подтвердить</button>
                    </div>
                    <p id="editMessage"></p>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="<@spring.url '/js/chart.js'/>"></script>
<script src="<@spring.url '/js/recommendation.js'/>"></script>
</body>
</html>
