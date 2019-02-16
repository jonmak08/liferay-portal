<section id="video">
    <div class="video-overlay">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="video-area">
                        <h2>
                            <@liferay.language key="watch-promo-video" />
                        </h2>

                        <a class="video-play-btn" href="#" id='kyTooltip' title="Click To Play">
                            <span aria-hidden="true" class="fa fa-play"></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="video-content">
        <div class="video-iframe-area">
            <a class="video-close-btn" href="#">
                <span aria-hidden="true" class="fa fa-times"></span>
            </a>

            <iframe class="video-iframe" frameborder="0" height="480" src="${VideoURL.getData()}" width="854" allowfullscreen></iframe>
        </div>
    </div>
</section>