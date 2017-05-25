$(document).ready(function() {
  var navbarStartingPosition = 0;
  var navbarCurrentPosition = $('.navbar-custom').offset().top;
  var navbar = $('.navbar-custom');

  // check if page loads with liferay-portal's control menu:
  if ($('.control-menu')) {
    $('#antonio-theme').addClass('theme-container');
    $('.navbar-custom').addClass('adjust-for-control-menu');

    var navbarStartingPosition = $('.control-menu').outerHeight();
  }

  // things to do on page load:
  if (navbarCurrentPosition > navbarStartingPosition) {
    navbar.addClass('navbar-white');
  } else {
    navbar.removeClass('navbar-white');
  }
  $('#pager-item-one').addClass('pager-item-selected');
  $('#testimonial-one').addClass('thumb-selected');

  $(document).scroll(function() {
    // will adjust navbarCurrentPosition when scrolling
    var navbarCurrentPosition = $('.navbar-custom').offset().top;
    if (navbarCurrentPosition > navbarStartingPosition) {
      navbar.addClass('navbar-white');
    } else {
      navbar.removeClass('navbar-white');
    }
  });

  $('#pager-item-one').click(function() {
    $('.welcome-container').removeClass('welcome-background-two welcome-background-three').addClass('welcome-background-one');
    $('#pager-item-one').addClass('pager-item-selected');
    $('#pager-item-two').removeClass('pager-item-selected');
    $('#pager-item-three').removeClass('pager-item-selected');
  });
  $('#pager-item-two').click(function() {
    $('.welcome-container').removeClass('welcome-background-one welcome-background-three').addClass('welcome-background-two');
    $('#pager-item-two').addClass('pager-item-selected');
    $('#pager-item-one').removeClass('pager-item-selected');
    $('#pager-item-three').removeClass('pager-item-selected');
  });
  $('#pager-item-three').click(function() {
    $('.welcome-container').removeClass('welcome-background-one welcome-background-two').addClass('welcome-background-three');
    $('#pager-item-three').addClass('pager-item-selected');
    $('#pager-item-one').removeClass('pager-item-selected');
    $('#pager-item-two').removeClass('pager-item-selected');
  });

  var testimonials = {
    one: {
      entry: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a elit massa. Proin vel consequat mi. Integer viverra enim a vulputate porttitor.',
      author: 'Antonio'
    },
    two: {
      entry: 'Vivamus eu facilisis nisi. Phasellus convallis, odio eu vehicula fermentum, ligula arcu aliquet lacus, vitae tristique diam purus eu sem.',
      author: 'Ryan'
    },
    three: {
      entry: 'Vestibulum volutpat consequat venenatis. Phasellus ullamcorper ex nibh, in sodales augue elementum in.',
      author: 'Josh'
    },
    four: {
      entry: 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam vitae arcu et ligula porttitor dapibus.',
      author: 'Jeff'
    },
    five: {
      entry: 'Nulla vehicula viverra nisl in bibendum. Curabitur et nibh at enim interdum efficitur sed vel augue. Suspendisse sagittis lacinia molestie.',
      author: 'Alex'
    }
  }

  $('#testimonial-one').click(function(event) {
    event.preventDefault();
    $('.testimonial-entry').html(testimonials.one.entry + '<br><br><span class="testimonial-author">' + testimonials.one.author + '</span>');
  });

  $('#testimonial-two').click(function(event) {
    event.preventDefault();
    $('.testimonial-entry').html(testimonials.two.entry + '<br><br><span class="testimonial-author">' + testimonials.two.author + '</span>');
  });

  $('#testimonial-three').click(function(event) {
    event.preventDefault();
    $('.testimonial-entry').html(testimonials.three.entry + '<br><br><span class="testimonial-author">' + testimonials.three.author + '</span>');
  });

  $('#testimonial-four').click(function(event) {
    event.preventDefault();
    $('.testimonial-entry').html(testimonials.four.entry + '<br><br><span class="testimonial-author">' + testimonials.four.author + '</span>');
  });

  $('#testimonial-five').click(function(event) {
    event.preventDefault();
    $('.testimonial-entry').html(testimonials.five.entry + '<br><br><span class="testimonial-author">' + testimonials.five.author + '</span>');
  });
});
