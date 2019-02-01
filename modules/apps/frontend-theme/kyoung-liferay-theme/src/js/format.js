(function($) {

    // Full Overlay Menu

    $('.mu-menu-btn').on('click', function(event) {
        event.preventDefault();
        $('.mu-menu-full-overlay').addClass('mu-menu-full-overlay-show');
    });

    // Remove overlay when close button clicked

    $('.mu-menu-close-btn').on('click', function(event) {
        event.preventDefault();
        $('.mu-menu-full-overlay').removeClass('mu-menu-full-overlay-show');
    });

    // Overlay dissapears after clicking menu item

    $('.mu-menu a').on('click', function(event) {
        $('.mu-menu-full-overlay').removeClass('mu-menu-full-overlay-show');
    });
    
})(jQuery);