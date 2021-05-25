<#import "/spring.ftl" as spring />
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
    <link rel="stylesheet" href="<@spring.url '/css/auction.css'/>">
</head>
<body>
<div class="background d-flex">
    <div class="left-menu d-flex flex-column justify-content-between">
        <div class="mt-5 d-flex justify-content-center">
            <img src="<@spring.url '/img/Logo-mini.png'/>" alt="">
        </div>
        <div class="d-flex flex-column align-items-center">
            <a class="my-4" href="<@spring.url '/recommendation'/>"><img
                        src="<@spring.url '/img/Recommendation-n.png'/>"
                        width="40" height="40" alt=""></a>
            <a class="my-4" href="<@spring.url '/recommendation'/>"><img src="<@spring.url '/img/Bill-n.png'/>"
                                                                         width="40" height="40" alt=""></a>
            <a class="my-4" href="<@spring.url '/auction'/>"><img src="<@spring.url '/img/Bet-n.png'/>"
                                                                  width="40" height="40" alt=""></a>
            <a class="my-4" href="<@spring.url '/myitems'/>"><img src="<@spring.url '/img/Search.png'/>"
                                                                  width="40" height="40" alt=""></a>
        </div>
        <div class="mb-5 d-flex justify-content-center">
            <a class="my-4" href="<@spring.url '/logout'/>"><img src="<@spring.url '/img/Logout.png'/>" width="40"
                                                                 height="40" alt=""></a>
        </div>
    </div>
    <div class="main d-flex justify-content-center align-items-center">
        <div class="row overflow-auto" style="width: 1600px; height: 900px;">
            <#list bets as b>
                <#if user.id == b.userId>
                    <div class="card my-3 ms-3 col-2" style="width: 18rem;">
                        <img src="<@spring.url '/files/${b.image}'/>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${b.description}</h5>
                            <div class="my-3 btn btn-outline-danger">${b.price}$</div>
                            <div class="mt-3 btn btn-outline-danger">On Auction</div>
                        </div>
                    </div>
                </#if>
            </#list>
            <#list primary as p>
                <#if user.id == p.userId>
                    <div class="card my-3 ms-3 col-2" style="width: 18rem;">
                        <img src="<@spring.url '/files/${p.image}'/>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title mb-3">${p.description}</h5>
                            <form action="<@spring.url '/sell/update'/>" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <input type="hidden" name="sellItemId" value="${p.id}">
                                <label>
                                    Price
                                    <input type="number" name="price">
                                </label>
                                <button type="submit" class="btn btn-primary mt-3">Update</button>
                            </form>
                        </div>
                    </div>
                </#if>
            </#list>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</body>
</html>