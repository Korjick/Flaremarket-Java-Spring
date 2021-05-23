<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Recommendation</title>
    <meta charset="UTF-8">
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
            <a class="my-4" href="<@spring.url '/recommendation'/>"><img src="<@spring.url '/img/Bet-n.png'/>"
                                                                         width="40" height="40" alt=""></a>
            <a class="my-4" href="<@spring.url '/recommendation'/>"><img src="<@spring.url '/img/Search-n.png'/>"
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
                    <a class="me-3" href="<@spring.url '/search'/>"><img src="<@spring.url '/img/SearchButton.png'/>"
                                                                         alt=""></a>
                    <div class="profile d-flex justify-content-center align-items-center">
                        <#if user.getProfileImage()??>
                            <img class="profile-image" id="profile-image"
                                 src="<@spring.url '/img/${user.getProfileImage().getProfileImageUrl()}'/>" alt=""
                                 width="40" height="40">
                        <#else>
                            <img class="profile-image" src="<@spring.url '/img/Default.png'/>" alt="" width="40"
                                 height="40">
                        </#if>
                        <div>
                            <a class="ms-2" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">${user.getNickname()}</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#sellModal"
                                       href="#">Sell Item</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
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
                    <div class="mt-3" id="stats"></div>
                </div>
                <div class="col-6">
                    <h1>Day Deal</h1>
                    <div class="mt-3 d-flex" id="daydeal">
                        <div class="deal" id="deal1"></div>
                        <div class="deal ms-5" id="deal2"></div>
                        <div class="deal ms-5" id="deal3"></div>
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
                                <li class="dropdown-item">12h.</li>
                                <li class="dropdown-item">8h.</li>
                                <li class="dropdown-item">1h.</li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <p>Name</p>
                            <div id="marketName" class="d-flex flex-column justify-content-center">

                            </div>
                        </div>
                        <div class="col-2">
                            <p>Price</p>
                            <div id="marketPrice" class="d-flex flex-column justify-content-center">

                            </div>
                        </div>
                        <div class="col-2">
                            <p>Chanee</p>
                            <div id="marketChanee" class="d-flex flex-column justify-content-center">

                            </div>
                        </div>
                        <div class="col-2">
                            <p>User</p>
                            <div id="marketUser" class="d-flex flex-column justify-content-center">

                            </div>
                        </div>
                        <div class="col-2">
                            <p>Watch</p>
                            <div id="marketWatch" class="d-flex flex-column justify-content-center">

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
    <!-- Modal -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="<@spring.url '/js/recommendation.js'/>"></script>
</body>
</html>
