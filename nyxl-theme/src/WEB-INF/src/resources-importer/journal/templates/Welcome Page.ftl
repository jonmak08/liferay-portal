<script>
    document.body.classList.add('js-loading');

    window.addEventListener("load", showPage);
    
    function showPage() {
      document.body.classList.remove('js-loading');
    }
    
</script>

<style>

    .js-loading *,
    .js-loading *:before,
    .js-loading *:after {
      animation-play-state: paused !important;
    }

    html body {
        padding-bottom: 0;
    }

    body::before, body::after {
        content: none;
    }
    
    section.container-fluid-1280 {
     max-width: none;   
    }
    
    #content {
        padding: 0;
    }
    
    #content::before {
        content: '';
        background-image: linear-gradient(45deg, #171c38 25%, #0f57ea 25%, #0f57ea 50%, #171c38 50%, #171c38 75%, #0f57ea 75%, #0f57ea);
        background-size: 150px 150px;
        animation: barberpole-body-case 1.5s ease-in-out 10;
        width: 50%;
        height: 51vh;
        top: 51%;
        position: absolute;
        z-index: -1;
        transform: translateY(-100%);
    }

    #content::after {
        content: '';
        background-image: linear-gradient(45deg, #171c38 25%, #0f57ea 25%, #0f57ea 50%, #171c38 50%, #171c38 75%, #0f57ea 75%, #0f57ea);
        background-size: 150px 150px;
        animation: barberpole-body-case 1.5s ease-in-out 10;
        width: 50%;
        height: 51vh;
        top: 51%;
        position: absolute;
        z-index: -1;
    transform: rotateX(180deg);
    }
    #main-content::before {
        content: '';
        background-image: linear-gradient(-45deg, 
#0f57ea 25%, 
#171c38 25%, 
#171c38 50%, 
#0f57ea 50%, 
#0f57ea 75%, 
#171c38 75%, 
#171c38);
        background-size: 150px 150px;
        animation: barberpole-body-case 1.5s ease-in-out 10;
        animation-direction: reverse;
        width: 50%;
        height: 51vh;
        top: 51%;
        right: 0;
        z-index: -1;
        position: absolute;
        transform: translateY(-100%);
    }

    #main-content::after {
        content: '';
        background-image: linear-gradient(-45deg, 
#0f57ea 25%, 
#171c38 25%, 
#171c38 50%, 
#0f57ea 50%, 
#0f57ea 75%, 
#171c38 75%, 
#171c38);
        background-size: 150px 150px;
        animation: barberpole-body-case 1.5s ease-in-out 10;
        animation-direction: reverse;
        width: 50%;
        height: 51vh;
        top: 51%;
        right: 0;
        z-index: -1;
        position: absolute;
    transform: rotateX(180deg);
    }
    
    .portlet-hello-world {
        display: flex;
        flex-direction: column;
        height: 67.5vh;
        justify-content: center;
        padding: 5%;
        margin: 0;
    }
    
    .welcome-container {
        height: 50vh;
    }
    
</style>

<div class="portlet-hello-world">
    <div class="welcome-container">
        <div class="welcome-content">
            <h1>${cityname.getData()}</h1>
            <h4>${teamname.getData()}</h4>
        </div>
    </div>
</div>