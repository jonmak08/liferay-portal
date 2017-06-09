<style>
.slide-one {
    background: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${Image1Url.getData()});
    background-attachment: fixed;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
    min-height: 400px;
}
.slide-two {
    background: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${Image2Url.getData()});
    background-attachment: fixed;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
    min-height: 400px;
}
.slide-three {
    background: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${Image3Url.getData()});
    background-attachment: fixed;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
    min-height: 400px;
}
</style>

<div class="slide-one text-center">
    <h1 class="carousel-image-title">${Image1Title.getData()}</h1>
    <div class="col-md-6 col-md-offset-3 text-center pager-container">
        <div class="pager-one pager-selected"><a href="#"></a></div>
        <div class="pager-two"><a href="#"></a></div>
        <div class="pager-three"><a href="#"></a></div>
    </div>
</div>

<div class="slide-two text-center">
    <h1 class="carousel-image-title">${Image2Title.getData()}</h1>
    <div class="col-md-6 col-md-offset-3 text-center pager-container">
        <div class="pager-one"><a href="#"></a></div>
        <div class="pager-two pager-selected"><a href="#"></a></div>
        <div class="pager-three"><a href="#"></a></div>
    </div>
</div>

<div class="slide-three text-center">
    <h1 class="carousel-image-title">${Image3Title.getData()}</h1>
    <div class="col-md-6 col-md-offset-3 text-center pager-container">
        <div class="pager-one"><a href="#"></a></div>
        <div class="pager-two"><a href="#"></a></div>
        <div class="pager-three pager-selected"><a href="#"></a></div>
    </div>
</div>
