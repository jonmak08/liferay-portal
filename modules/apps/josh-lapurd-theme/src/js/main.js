AUI().ready(
    'liferay-sign-in-modal',
    function( A ) {
        var signIn = A.one( '#signIn' );

        if ( signIn && signIn.getData( 'redirect' ) !== 'true' ) {
            signIn.plug( Liferay.SignInModal );
        }
    }
);

AUI.$( 'a.theme-nav-bar-toggle' ).click(
    function() {
        AUI.$( '#themeNavBar' ).toggleClass( 'menu-open' );
    }
);

AUI.$( '#childMenuDropdown_1' ).click(
    function() {
        var currTarget = AUI.$( this );
        var currTarSib = currTarget.siblings( 'ul.child-menu' );

        if ( currTarSib !== undefined ) {
            currTarSib.toggleClass( 'child-menu-open' );
            currTarget.toggleClass( 'child-menu-toggle-open' );
        }
    }
);

AUI.$( '#childMenuDropdown_2' ).click(
    function() {
        var currTarget = AUI.$( this );
        var currTarSib = currTarget.siblings( 'ul.child-menu' );

        if ( currTarSib !== undefined ) {
            currTarSib.toggleClass( 'child-menu-open' );
            currTarget.toggleClass( 'child-menu-toggle-open' );
        }
    }
);

AUI.$( '.btn-btt' ).click(
    function() {
        AUI.$( 'html, body' ).animate( 
            { scrollTop : 0 }, 
            700
        );
        return false;
    }
);

AUI().ready(
    'scroll-transparency',
    function( A ) {
        A.on( 'scroll', function() {
            var scrollY = window.pageYOffset;
            var buttonBtt = AUI.$( '.btn-btt' );
            var banner = AUI.$( '#banner' );

            if ( scrollY > 0 && banner.hasClass( 'banner-unscrolled' ) ) {
                banner.toggleClass( 'banner-scrolled banner-unscrolled' );
                buttonBtt.fadeIn();
            } else if ( scrollY === 0 && banner.hasClass( 'banner-scrolled' ) ) {
                banner.toggleClass( 'banner-scrolled banner-unscrolled' );
                buttonBtt.fadeOut();
            }
        } );
    }
);
