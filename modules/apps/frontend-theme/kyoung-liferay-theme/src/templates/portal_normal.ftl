<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
  <head>
  	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Airbnb App Template - Kevin Young</title>
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/icon" href="/o/kyoung-liferay-theme/images/favicon.ico"/>
    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="/o/kyoung-liferay-theme/css/bootstrap.min.css" rel="stylesheet">
    <!-- Slick slider -->
    <link href="\o\kyoung-liferay-theme\css\slick.css" rel="stylesheet">

    <#--  <link href="style.css" rel="stylesheet">  -->

    <!-- Open Sans for body and title font -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,400i,600,700,800" rel="stylesheet">
 	<@liferay_util["include"] page=top_head_include />

 
  </head>
<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />  	
<!-- Start Header -->
	<header id="mu-header" class="" role="banner">
		<div class="mu-header-overlay">
			<div class="container">
				<div class="mu-header-area">

					<!-- Start Logo -->
					<div class="mu-logo-area">
						<!-- text based logo -->
						<!-- <a class="mu-logo" href="#">Airbnb App</a> -->
						<!-- image based logo -->
						<a class="mu-logo" href="#"><img src="/o/kyoung-liferay-theme/images/logo.png" alt="logo img"></a>
					</div>
					<!-- End Logo -->

					<!-- Start header featured area -->
					<div class="mu-header-featured-area">
						<div class="mu-header-featured-img">
							<img src="/o/kyoung-liferay-theme/images/iphone.png" alt="iphone image">
						</div>

						<div class="mu-header-featured-content">
							<h1>Welcome To <span>Airbnb</span></h1>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Architecto nisi, consectetur odit voluptate labore veniam, neque iure cumque aut recusandae iusto. Aliquid quos vel optio dolore consequuntur accusantium autem quaerat!</p>

							<div class="mu-app-download-area">
								<h4>Download The App</h4>
								<a class="mu-apple-btn" href="#"><i class="fa fa-apple"></i><span>apple store</span></a>
								<a class="mu-google-btn" href="#"><i class="fa fa-android"></i><span>google play</span></a>
								<!-- <a class="mu-windows-btn" href="#"><i class="fa fa-windows"></i><span>windows store</span></a> -->
							</div>

						</div>
					</div>
					<!-- End header featured area -->

				</div>
			</div>
		</div>
	</header>
	<!-- End Header -->

	<!-- Start Menu -->
	<button class="mu-menu-btn">
		<i class="fa fa-bars"></i>
	</button>
	<div class="mu-menu-full-overlay">
		<div class="mu-menu-full-overlay-inner">
			<a class="mu-menu-close-btn" href="#"><span class="mu-line"></span></a>
			<nav class="mu-menu" role="navigation">
				<ul>
					<li><a href="#mu-header">Header</a></li>
					<li><a href="#mu-feature">App Feature</a></li>
					<li><a href="#mu-video">Promo Video</a></li>
					<li><a href="#mu-apps-screenshot">Apps Screenshot</a></li>
					<li><a href="#mu-download">Download</a></li>
					<li><a href="#mu-faq">FAQ</a></li>
					<li><a href="#mu-contact">Get In Touch</a></li>
				</ul>
			</nav>
		</div>
	</div>
	<!-- End Menu -->

	
	
	<!-- Start main content -->
		
	<main role="main">

		<!-- Start Feature -->
		<section id="mu-feature">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="mu-feature-area">

							<div class="mu-title-area">
								<h2 class="mu-title">OUR APP FEATURES</h2>
								<span class="mu-title-dot"></span>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis unde, ut sapiente et voluptatum facilis consectetur incidunt provident asperiores at necessitatibus nulla sequi voluptas libero quasi explicabo veritatis minima porro.</p>
							</div>

							
							<!-- Start Feature Content -->
							<div class="mu-feature-content">
								<div class="row">
									<div class="col-md-6">
										<div class="mu-feature-content-left">
											<img class="mu-profile-img" style="margin-top: 80px" src="/o/kyoung-liferay-theme/images/iphone-group.png" alt="iphone Image">
										</div>
									</div>
									<div class="col-md-6">
										<div class="mu-feature-content-right">

											<!-- Start single feature item -->
											<div class="media">
						                        <div class="media-left">
						                        	<button class="btn mu-feature-btn" type="button">
						                        		<i class="fa fa-tablet" aria-hidden="true"></i>
						                        	</button>
						                        </div>
						                        <div class="media-body">
							                        <h3 class="media-heading">Responsive Design</h3>
							                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Hic at, omnis neque illum accusamus delectus consectetur perspiciatis fugiat ex veniam sit soluta iste, reprehenderit laboriosam!</p>
						                        </div>
						                    </div>
						                    <!-- End single feature item -->

						                    <!-- Start single feature item -->
											<div class="media">
						                        <div class="media-left">
						                        	<button class="btn mu-feature-btn" type="button">
						                        		<i class="fa fa-sliders" aria-hidden="true"></i>
						                        	</button>
						                        </div>
						                        <div class="media-body">
							                        <h3 class="media-heading">Easy To Customize</h3>
							                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perspiciatis provident in voluptatum nihil suscipit molestias, excepturi blanditiis corrupti error tenetur ab, voluptates architecto nemo.</p>
						                        </div>
						                    </div>
						                    <!-- End single feature item -->

						                    <!-- Start single feature item -->
											<div class="media">
						                        <div class="media-left">
						                        	<button class="btn mu-feature-btn" type="button">
						                        		<i class="fa fa-tachometer" aria-hidden="true"></i>
						                        	</button>
						                        </div>
						                        <div class="media-body">
							                        <h3 class="media-heading">Excellent Performance</h3>
							                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dicta veniam eius, ullam nisi repellendus deserunt distinctio, eveniet libero velit quo voluptatem sequi, ipsa asperiores ad? Facilis molestiae cum aliquam.</p>
						                        </div>
						                    </div>
						                    <!-- End single feature item -->

						                    <!-- Start single feature item -->
											<div class="media">
						                        <div class="media-left">
						                        	<button class="btn mu-feature-btn" type="button">
						                        		<i class="fa fa-map-marker" aria-hidden="true"></i>
						                        	</button>
						                        </div>
						                        <div class="media-body">
							                        <h3 class="media-heading">GPS Tracking</h3>
							                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque repudiandae eveniet facere natus recusandae, sapiente iste veritatis molestiae. Recusandae veniam officia asperiores reprehenderit earum.</p>
						                        </div>
						                    </div>
						                    <!-- End single feature item -->

										</div>
									</div>
								</div>
							</div>
							<!-- End Feature Content -->

						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Feature -->

		<!-- Start Video -->
		<section id="mu-video">
			<div class="mu-video-overlay">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="mu-video-area">
								<h2>Watch Promo Video</h2>
								<a class="mu-video-play-btn" href="#"><i class="fa fa-play" aria-hidden="true"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Start Video content -->
			<div class="mu-video-content">
				<div class="mu-video-iframe-area">
					<a class="mu-video-close-btn" href="#"><i class="fa fa-times" aria-hidden="true"></i></a>
					<!-- <iframe class="mu-video-iframe" width="854" height="480" src="https://www.youtube.com/embed/yetFk7QoSck" frameborder="0" allowfullscreen></iframe>  -->
				</div>
			</div>
			<!-- End Video content -->

		</section>
		<!-- End Video -->

		<!-- Start Apps Screenshot -->
		<section id="mu-apps-screenshot">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="mu-apps-screenshot-area">

							<div class="mu-title-area">
								<h2 class="mu-title">APPS SCREENSHOT</h2>
								<span class="mu-title-dot"></span>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis unde, ut sapiente et voluptatum facilis consectetur incidunt provident asperiores at necessitatibus nulla sequi voluptas libero quasi explicabo veritatis minima porro.</p>
							</div>


							<!-- Start Apps Screenshot Content -->
							<div class="mu-apps-screenshot-content">

								<div class="mu-apps-screenshot-slider">

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/01.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/02.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/03.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/04.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/05.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/01.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/02.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/03.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/04.jpg" alt="App screenshot img">
									</div>

									<div class="mu-single-screeshot">
										<img src="/o/kyoung-liferay-theme/images/screenshot/05.jpg" alt="App screenshot img">
									</div>

								</div>

							</div>
							<!-- End Apps Screenshot Content -->

						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Apps Screenshot -->

		<!-- Start Download -->
		<section id="mu-download">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="mu-download-area">

							<div class="mu-title-area">
								<h2 class="mu-title">GET THE APP</h2>
								<span class="mu-title-dot"></span>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis unde, ut sapiente et voluptatum facilis consectetur incidunt provident asperiores at necessitatibus nulla sequi voluptas libero quasi explicabo veritatis minima porro.</p>
							</div>


							<div class="mu-download-content">
								<a class="mu-apple-btn" href="#"><i class="fa fa-apple"></i><span>apple store</span></a>
								<a class="mu-google-btn" href="#"><i class="fa fa-android"></i><span>google play</span></a>
								<a class="mu-windows-btn" href="#"><i class="fa fa-windows"></i><span>windows store</span></a>
							</div>


						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Download -->

		<!-- Start FAQ -->
		<section id="mu-faq">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="mu-faq-area">

							<div class="mu-title-area">
								<h2 class="mu-title">FAQ</h2>
								<span class="mu-title-dot"></span>
							</div>


							<div class="mu-faq-content">

								<div class="panel-group" id="accordion">

							        <div class="panel panel-default">
							          <div class="panel-heading">
							            <h4 class="panel-title">
							              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true">
							                <span class="fa fa-minus"></span> What is Airbnb App? 
							              </a>
							            </h4>
							          </div>
							          <div id="collapseOne" class="panel-collapse collapse in">
							            <div class="panel-body">
							              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
							            </div>
							          </div>
							        </div>

							        <div class="panel panel-default">
							          <div class="panel-heading">
							            <h4 class="panel-title">
							              <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
							                <span class="fa fa-plus"></span> How do I setup this App? 
							              </a>
							            </h4>
							          </div>
							          <div id="collapseTwo" class="panel-collapse collapse">
							            <div class="panel-body">
							              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
							            </div>
							          </div>
							        </div>

							        <div class="panel panel-default">
							          <div class="panel-heading">
							            <h4 class="panel-title">
							              <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
							                <span class="fa fa-plus"></span> Does it cost anything to become a member? 
							              </a>
							            </h4>
							          </div>
							          <div id="collapseThree" class="panel-collapse collapse">
							            <div class="panel-body">
							              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
							            </div>
							          </div>
							        </div>

							        <div class="panel panel-default">
							          <div class="panel-heading">
							            <h4 class="panel-title">
							              <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
							                <span class="fa fa-plus"></span> What is your policy regarding privacy? 
							              </a>
							            </h4>
							          </div>
							          <div id="collapseFour" class="panel-collapse collapse">
							            <div class="panel-body">
							              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
							            </div>
							          </div>
							        </div>

							        <div class="panel panel-default">
							          <div class="panel-heading">
							            <h4 class="panel-title">
							              <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
							                <span class="fa fa-plus"></span> Are there more help resources available? 
							              </a>
							            </h4>
							          </div>
							          <div id="collapseFive" class="panel-collapse collapse">
							            <div class="panel-body">
							              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
							            </div>
							          </div>
							        </div>


							    </div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End FAQ -->


		<!-- Start Contact -->
		<section id="mu-contact">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="mu-contact-area">

							<div class="mu-title-area">
								<h2 class="mu-heading-title">GET IN TOUCH</h2>
								<span class="mu-title-dot"></span>
								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever</p>
							</div>

							

							<!-- Start Contact Content -->
							<div class="mu-contact-content">
								<div class="row">

								<div class="col-md-7">
									<div class="mu-contact-left">
										<div id="form-messages"></div>
											<form id="ajax-contact" method="post" action="mailer.php" class="mu-contact-form">
												<div class="form-group">                
													<input type="text" class="form-control" placeholder="Name" id="name" name="name" required>
												</div>
												<div class="form-group">                
													<input type="email" class="form-control" placeholder="Enter Email" id="email" name="email" required>
												</div>              
												<div class="form-group">
													<textarea class="form-control" placeholder="Message" id="message" name="message" required></textarea>
												</div>
												<button type="submit" class="mu-send-msg-btn"><span>SUBMIT</span></button>
								            </form>
										</div>
									</div>

									<div class="col-md-5">
										<div class="mu-contact-right">
											<div class="mu-contact-right-single">
												<div class="mu-icon"><i class="fa fa-map-marker"></i></div>
												<p><strong>Office Location</strong></p>
												<p>123 Fake Street, Irvine, CA, USA</p>
											</div>

											<div class="mu-contact-right-single">
												<div class="mu-icon"><i class="fa fa-phone"></i></div>
												<p><strong>Phone Number</strong></p>
												<p>+1 (949) 999-9999</p>
												<p>+1 (949) 999-9999</p>
											</div>

											<div class="mu-contact-right-single">
												<div class="mu-icon"><i class="fa fa-envelope"></i></div>
												<p><strong>Email Address</strong></p>
												<p>contact@airbnb.com</p>
												<p>support@airbnb.com</p>
											</div>

											<div class="mu-contact-right-single">
												<div class="mu-social-media">
													<a href="#"><i class="fa fa-facebook"></i></a>
													<a href="#"><i class="fa fa-twitter"></i></a>
													<a href="#"><i class="fa fa-google-plus"></i></a>
													<a href="#"><i class="fa fa-linkedin"></i></a>
													<a href="#"><i class="fa fa-youtube"></i></a>
												</div>
											</div>

										</div>
									</div>		

								</div>
							</div>
							<!-- End Contact Content -->

						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Contact -->

	</main>
	
	<!-- End main content -->	
			
			
	<!-- Start footer -->
	<footer id="mu-footer" role="contentinfo">
			<div class="container">
				<div class="mu-footer-area">
					<p class="mu-copy-right">&copy; Copyright <a rel="nofollow" href="https://www.kevin-young.us">kevin-young.us</a>. All right reserved.</p>
				</div>
			</div>

	</footer>
	<!-- End footer -->

	
	
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <!-- Bootstrap -->
    <script src="/o/kyoung-liferay-theme/js/bootstrap.min.js"></script>
	<!-- Slick slider -->
    <script type="text/javascript" src="/o/kyoung-liferay-theme/js/slick.min.js"></script>
    <!-- Ajax contact form  -->
    <script type="text/javascript" src="/o/kyoung-liferay-theme/js/app.js"></script>
  
    
	
    <!-- Custom js -->
	<script type="text/javascript" src="/o/kyoung-liferay-theme/js/custom.js"></script>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>


"/o/kyoung-liferay-theme/images/iphone6.png"