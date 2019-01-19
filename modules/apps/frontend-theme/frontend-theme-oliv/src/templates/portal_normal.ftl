<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
    <title>${the_title} - ${company_name}</title>
    <meta content="initial-scale=1.0, width=device-width" name="viewport" />
    <link href="https://fonts.googleapis.com/css?family=Lato|Oswald" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <@liferay_util["include"] page=top_head_include />
</head>

<body>

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

    <body>
        <header class="site-header">
            <div class="top-bar">
                <nav class="social-navigation">
                    <ul id="menu-social">
                        <li>
                            <a href="http://facebook.com" target="_blank">
                                <i class="fab fa-facebook-f"></i>
                            </a>
                        </li>
                        <li>
                            <a href="http://instagram.com" target="_blank">
                                <i class="fab fa-instagram"></i>
                            </a>
                        </li>
                        <li>
                            <a href="http://twitter.com" target="_blank">
                                <i class="fab fa-twitter"></i>
                            </a>
                        </li>
                        <li>
                            <a href="http://linkedin.com" target="_blank">
                                <i class="fab fa-linkedin-in"></i>
                            </a>
                        </li>
                        <li>
                            <a href="http://pinterest.com" target="_blank">
                                <i class="fab fa-pinterest"></i>
                            </a>
                        </li>
                    </ul>
                </nav>
                <div class="sidebar-toggle">
                    <i class="fas fa-plus"></i>
                </div>
            </div>
            <div class="container">
                <div class="site-branding">
                    <h1 class="site-title">Oria Demo</h1>
                    <h2 class="site-description">Just another Liferay theme by JustFreeThemes</h2>
                </div>
            </div>
            <nav id="site-navigation" class="main-navigation" role="navigation">
                <div class="menu-container">
                    <ul id="primary-menu" class="menu clearfix">
                        <li id="menu-item">
                            <a href="home.asp">Home</a>
                        </li>
                        <li id="menu-item">
                            <a href="home.asp">Sample Page</a>
                        </li>
                        <li id="menu-item">
                            <a href="home.asp">Full Width</a>
                        </li>
                        <li id="menu-item">
                            <a href="home.asp">Dropdown</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <div class="oria-slider">
            <div class="featured-inner clearfix">
                <div class="slider-inner carousel">
                    <div class="owl-wrapper-outer">
                        <div class="owl-wrapper">
                            <div class="owl-item">
                                <div class="slide">
                                    <img src="http://oria.madalinm.com/wp-content/uploads/2017/07/33272284305_4b0757b48c_b_lxdfwe-390x260.jpg" class="owl-post-image">
                                </div>
                                <h3 class="slide-title">
                                    <a href="http://oria.madalinm.com/2017/07/27/passing-by/">Passing By</a>
                                </h3>
                            </div>
                            <div class="owl-item">
                                <div class="slide">
                                    <img src="http://oria.madalinm.com/wp-content/uploads/2017/07/32429453034_914102e898_b_uhodku-390x260.jpg" class="owl-post-image">
                                </div>
                                <h3 class="slide-title">
                                    <a href="http://oria.madalinm.com/2017/07/27/path/">Path</a>
                                </h3>
                            </div>
                            <div class="owl-item">
                                <div class="slide">
                                    <img src="http://oria.madalinm.com/wp-content/uploads/2017/07/32429436324_fe8edfbdec_b_mitqlz-390x260.jpg" class="owl-post-image">
                                </div>
                                <h3 class="slide-title">
                                    <a href="http://oria.madalinm.com/2017/07/27/hello-world/">Hello World</a>
                                </h3>
                            </div>
                            <div class="owl-item">
                                <div class="slide">
                                    <img src="http://oria.madalinm.com/wp-content/uploads/2017/07/33231141446_5d1d4e8e15_b_n7heil-390x260.jpg" class="owl-post-image">
                                </div>
                                <h3 class="slide-title">
                                    <a href="http://oria.madalinm.com/2017/07/27/skate-park/">Skate Park</a>
                                </h3>
                            </div>
                            <div class="owl-item">
                                <div class="slide">
                                    <img src="http://oria.madalinm.com/wp-content/uploads/2017/07/31638288096_19af71e92d_b_kgiiwt-390x260.jpg" class="owl-post-image">
                                </div>
                                <h3 class="slide-title">
                                    <a href="http://oria.madalinm.com/2017/07/27/winter-hat/">Winter Hat</a>
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="owl-controls clickable">
                        <div class="owl-buttons">
                            <div class="owl-prev">
                                <i class="fas fa-long-arrow-alt-left"></i>
                            </div>
                            <div class="owl-next">
                                <i class="fas fa-long-arrow-alt-right"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="content" class="site-content clearfix">
            <div id="primary" class="content-area fullwidth">
                <main id="main" class="site-main" role="main">
                    <div class="posts-layout">
                        <article id="post" class="hentry">
                            <div class="item-sizer">
                                <div class="entry-thumb">
                                    <a href="http://oria.madalinm.com/2017/07/27/city-view/" title="City View"><img width="520" height="347" src="http://oria.madalinm.com/wp-content/uploads/2017/07/31675144995_8d2d42077d_b_bo38ub-520x347.jpg" class="small-thumb" alt=""></a>
                                    <header class="entry-header blog-entry-header">
                                        <div class="entry-data">
                                            <span class="posted-on">
                                                <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">
                                                    <time class="entry-date published" datetime="2017-07-27T09:27:52+00:00">July 27, 2017</time>
                                                    <time class="updated" datetime="2017-07-27T09:32:36+00:00">July 27, 2017</time></a>
                                                </a>
                                            </span>
                                            <span>/</span>
                                            <span class="cat-links"><a href="http://oria.madalinm.com/category/city/" rel="category tag">City</a>, <a href="http://oria.madalinm.com/category/city-view/" rel="category tag">City View</a></span>
                                        </div>
                                        <h1 class="entry-title">
                                            <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">City View</a>
                                        </h1>
                                    </header>
                                    <div class="entry-content">
                                        <p>The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and
                                        <a class="read-more" href="http://oria.madalinm.com/2017/07/27/city-view/">Continue reading</a>
                                    </div>
                                </div>
                            </div>
                        </article>
                        <article id="post" class="hentry">
                            <div class="item-sizer">
                                <div class="entry-thumb">
                                    <a href="http://oria.madalinm.com/2017/07/27/city-view/" title="Winter Hat"><img width="520" height="347" src="http://oria.madalinm.com/wp-content/uploads/2017/07/31638288096_19af71e92d_b_kgiiwt-520x348.jpg" class="small-thumb" alt=""></a>
                                    <header class="entry-header blog-entry-header">
                                        <div class="entry-data">
                                            <span class="posted-on">
                                                <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">
                                                    <time class="entry-date published" datetime="2017-07-27T09:27:52+00:00">July 27, 2017</time>
                                                    <time class="updated" datetime="2017-07-27T09:32:36+00:00">July 27, 2017</time></a>
                                                </a>
                                            </span>
                                            <span>/</span>
                                            <span class="cat-links"><a href="http://oria.madalinm.com/category/city/" rel="category tag">City</a>, <a href="http://oria.madalinm.com/category/city-view/" rel="category tag">Winter Hat</a></span>
                                        </div>
                                        <h1 class="entry-title">
                                            <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">Winter Hat</a>
                                        </h1>
                                    </header>
                                    <div class="entry-content">
                                        <p>The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and
                                        <a class="read-more" href="http://oria.madalinm.com/2017/07/27/city-view/">Continue reading</a>
                                    </div>
                                </div>
                            </div>
                        </article>
                        <article id="post" class="hentry">
                            <div class="item-sizer">
                                <div class="entry-thumb">
                                    <a href="http://oria.madalinm.com/2017/07/27/city-view/" title="Passing By"><img width="520" height="347" src="http://oria.madalinm.com/wp-content/uploads/2017/07/33272284305_4b0757b48c_b_lxdfwe.jpg" class="small-thumb" alt=""></a>
                                    <header class="entry-header blog-entry-header">
                                        <div class="entry-data">
                                            <span class="posted-on">
                                                <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">
                                                    <time class="entry-date published" datetime="2017-07-27T09:27:52+00:00">July 27, 2017</time>
                                                    <time class="updated" datetime="2017-07-27T09:32:36+00:00">July 27, 2017</time></a>
                                                </a>
                                            </span>
                                            <span>/</span>
                                            <span class="cat-links"><a href="http://oria.madalinm.com/category/city/" rel="category tag">City</a>, <a href="http://oria.madalinm.com/category/city-view/" rel="category tag">Winter Hat</a></span>
                                        </div>
                                        <h1 class="entry-title">
                                            <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">Passing By</a>
                                        </h1>
                                    </header>
                                    <div class="entry-content">
                                        <p>The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and
                                        <a class="read-more" href="http://oria.madalinm.com/2017/07/27/city-view/">Continue reading</a>
                                    </div>
                                </div>
                            </div>
                        </article>
                        <article id="post" class="hentry">
                            <div class="item-sizer">
                                <div class="entry-thumb">
                                    <a href="http://oria.madalinm.com/2017/07/27/city-view/" title="Path"><img width="520" height="347" src="http://oria.madalinm.com/wp-content/uploads/2017/07/32429453034_914102e898_b_uhodku.jpg" class="small-thumb" alt=""></a>
                                    <header class="entry-header blog-entry-header">
                                        <div class="entry-data">
                                            <span class="posted-on">
                                                <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">
                                                    <time class="entry-date published" datetime="2017-07-27T09:27:52+00:00">July 27, 2017</time>
                                                    <time class="updated" datetime="2017-07-27T09:32:36+00:00">July 27, 2017</time></a>
                                                </a>
                                            </span>
                                            <span>/</span>
                                            <span class="cat-links"><a href="http://oria.madalinm.com/category/city/" rel="category tag">City</a>, <a href="http://oria.madalinm.com/category/city-view/" rel="category tag">Winter Hat</a></span>
                                        </div>
                                        <h1 class="entry-title">
                                            <a href="http://oria.madalinm.com/2017/07/27/city-view/" rel="bookmark">Path</a>
                                        </h1>
                                    </header>
                                    <div class="entry-content">
                                        <p>The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and
                                        <a class="read-more" href="http://oria.madalinm.com/2017/07/27/city-view/">Continue reading</a>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </div>
                </main>
            </div>
        </div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>