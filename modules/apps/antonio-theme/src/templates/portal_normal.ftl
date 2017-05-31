<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>
	<link rel="stylesheet" href="../css/_custom.css">
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="page-content">
	<header id="banner" role="banner">

		<#if has_navigation && is_setup_complete>
		<#include "${full_templates_path}/navigation.ftl" />
		</#if>

	</header>

	<div class="welcome-container">
	  <div id="carouselSlideOne" class="welcome-background-one">
	    <div class="welcome-content container">
	      <div class="welcome-header">
	        <h2>welcome to our restaurant</h2>
	      </div>
	      <div class="row">
	        <div class="welcome-description col-md-6 col-md-offset-3">
	          <p>
	            Tumblr next level actually helvetica. Health goth tattooed fam mlkshk, blue bottle bicycle rights paleo. Mumblecore crucifix sartorial tote bag lyft, la croix skateboard messenger bag tacos fanny pack organic. Crucifix offal meh tbh. IPhone bitters kinfolk
	            waistcoat, seitan master cleanse farm-to-table.
	          </p>
	          <div class="welcome-more-info">
	            <a href="#">read our story</a>
	          </div>
	          <!-- .more-info -->
	        </div>
	        <!-- .welcome-description .col-md-6 .col-md-offset-3 -->
	      </div>
	      <!-- .row -->
	    </div>
	    <!-- .welcome-content .container -->

	    <div class="col-md-6 col-md-offset-3 text-center">
	      <div class="pagination">
	        <div class="pager-item-one pager-item-selected">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	        <div class="pager-item-two">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	        <div class="pager-item-three">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	      </div>
	      <!-- .pagination -->
	    </div>
	    <!-- .col-md-6 .col-md-offset-3 -->
	  </div>
	  <!-- #carouselSlideOne .welcome-container .welcome-background-one -->
	</div>
	<!-- .welcome-container -->

	<div class="welcome-container">
	  <div id="carouselSlideTwo" class="welcome-background-two">
	    <div class="welcome-content container">
	      <div class="welcome-header">
	        <h2>we serve delicious dishes</h2>
	      </div>
	      <div class="row">
	        <div class="welcome-description col-md-6 col-md-offset-3">
	          <p>
	            Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer euismod lacus luctus magna. Quisque cursus, metus vitae pharetra auctor, sem massa mattis sem, at interdum magna augue eget diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Morbi lacinia molestie dui. Praesent blandit dolor. Sed non quam. In vel mi sit amet augue congue elementum.
	          </p>
	          <div class="welcome-more-info">
	            <a href="#">read more</a>
	          </div>
	          <!-- .more-info -->
	        </div>
	        <!-- .welcome-description .col-md-6 .col-md-offset-3 -->
	      </div>
	      <!-- .row -->
	    </div>
	    <!-- .welcome-content .container -->

	    <div class="col-md-6 col-md-offset-3 text-center">
	      <div class="pagination">
	        <div class="pager-item-one">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	        <div class="pager-item-two pager-item-selected">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	        <div class="pager-item-three">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	      </div>
	      <!-- .pagination -->
	    </div>
	    <!-- .col-md-6 .col-md-offset-3 -->
	  </div>
	  <!-- #carouselSlideTwo .welcome-container .welcome-background-two -->
	</div>
	<!-- .welcome-container -->

	<div class="welcome-container">
	  <div id="carouselSlideThree" class="welcome-container welcome-background-three">
	    <div class="welcome-content container">
	      <div class="welcome-header">
	        <h2>enjoy the best live music</h2>
	      </div>
	      <div class="row">
	        <div class="welcome-description col-md-6 col-md-offset-3">
	          <p>
	            Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis.
	          </p>
	          <div class="welcome-more-info">
	            <a href="#">view schedule</a>
	          </div>
	          <!-- .more-info -->
	        </div>
	        <!-- .welcome-description .col-md-6 .col-md-offset-3 -->
	      </div>
	      <!-- .row -->
	    </div>
	    <!-- #carouselSlideOne .welcome-content .container -->

	    <div class="col-md-6 col-md-offset-3 text-center">
	      <div class="pagination">
	        <div class="pager-item-one">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	        <div class="pager-item-two">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	        <div class="pager-item-three pager-item-selected">
	          <a href="#"></a>
	        </div>
	        <!-- .pager-item -->
	      </div>
	      <!-- .pagination -->
	    </div>
	    <!-- .col-md-6 .col-md-offset-3 -->
	  </div>
	  <!-- #carouselSlideThree .welcome-container .welcome-background-three -->
	</div>
	<!-- .welcome-container -->

	<main id="content" role="main">
		<div class="container">
			<h1 class="hide-accessible">${the_title}</h1>

			<#if selectable>
				<@liferay_util["include"] page=content_include />
				<#else>
				${portletDisplay.recycle()}

				${portletDisplay.setTitle(the_title)}

				<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
				</@>
			</#if>
		</div>
	</main>

	<#include "${full_templates_path}/footer.ftl" />

</div>
<!-- .page-content -->

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>
<script src="../js/main.js" charset="utf-8"></script>
</html>
