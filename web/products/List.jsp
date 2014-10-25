<%-- 
    Document   : newjsf
    Created on : Oct 25, 2014, 10:29:41 AM
    Author     : Lucre Ck
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <!-- =========================================
            Basic
            ========================================== -->
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>Decor</title>
            <meta name="keywords" content="Decor, HTML5, CSS3, responsive, Template" />
            <meta name="author" content="Cloud Software" />
            <meta name="robots" content="index follow"/>
            <meta name="description" content="Decor - Responsive HTML5/CSS3 Template" />

            <!-- =========================================
            Mobile Configurations
            ========================================== -->
            <meta name="viewport" content="width=device-width, initial-scale=1.0 user-scalable=no" />
            <meta name="apple-mobile-web-app-status-bar-style" content="black" />
            <meta name="GOOGLEBOT" content="index follow"/>
            <meta name="apple-mobile-web-app-capable" content="yes" />


            <!-- Fonts -->
            <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic' rel='stylesheet' type='text/css'>
            <link rel="stylesheet" href="../css/font-awesome.min.css" />
            <link rel="stylesheet" href="../css/icostyle.css" />
            <!-- //Fonts -->


            <!-- Normalize CSS -->
            <link rel="stylesheet" href="../css/normalize.css" />

            <!-- Owl Carousel CSS -->
            <link href="../css/owl.carousel.css" rel="stylesheet" media="screen">	



            <!-- =========================================
            CSS
            ========================================== -->	
            <link rel="stylesheet" href="../css/bootstrap.min.css" />

            <link rel="stylesheet" href="../css/prettyPhoto.css" />

            <link rel="stylesheet" href="../css/offcanvas.css" />
            <link href="../style.css" rel="stylesheet" type="text/css"/>
            <link href="../css/responsive.css" rel="stylesheet" media="screen" />


            <!-- =========================================
            Head Libs
            ========================================== -->
            <script src="../js/modernizr-2.6.2.min.js"></script>

            <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
            <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
            <script src="../js/respond.min.js"></script>
            <![endif]-->		



        </head>

        <body>
            <!-- wrapper -->
            <div class="wrapper offcanvas-container" id="offcanvas-container">

                <!-- inner-wrapper -->
                <div class="inner-wrapper offcanvas-pusher">
                    <!-- container -->
                    <div class="container">
                        <!-- header-wrapper -->
                        <header class="header-wrapper clearfix">
                            <!-- row -->
                            <div class="row">
                                <!-- col-xs-12 -->
                                <div class="col-xs-12">

                                    <!-- Logo -->
                                    <div class="logo">
                                        <a title="Decor" href="index.html">
                                            <img src="../img/logo.png" alt="Deccor" >
                                        </a>
                                    </div>
                                    <!-- /Logo -->	


                                    <!-- Top Social Icon -->
                                    <div class="social-icon hidden-xs clearfix">
                                        <ul>
                                            <li>
                                                <!-- facebook -->
                                                <a href="#" title="Facebook"  target="_blank"><i class="fa fa-facebook"> </i></a>
                                                <!-- /facebook -->
                                            </li>

                                            <li>
                                                <!-- twitter -->
                                                <a href="#" title="Twitter" target="_blank"><i class="fa fa-twitter"></i></a>
                                                <!-- /twitter -->
                                            </li>

                                            <li>
                                                <!-- google-plus -->
                                                <a href="#" title="Google plus" target="_blank"><i class="fa fa-google-plus"></i></a>
                                                <!-- /google-plus -->
                                            </li>

                                            <li>
                                                <!-- youtube -->
                                                <a href="#" title="YouTube" target="_blank"><i class="fa fa-youtube"></i></a>
                                                <!-- /youtube-play -->
                                            </li>

                                            <li>
                                                <!-- linkedin -->
                                                <a href="#" title="Linkedin" target="_blank"><i class="fa fa-linkedin"></i></a>
                                                <!-- /linkedin -->
                                            </li>

                                            <li>
                                                <!-- flickr -->
                                                <a href="#" title="Flickr" target="_blank"><i class="fa fa-flickr"></i></a>
                                                <!-- /flickr -->
                                            </li>
                                        </ul>
                                    </div><!-- end of Top Social Icon -->



                                    <!-- =========================================
                                    Menu
                                    ========================================== -->
                                    <!-- navbar -->
                                    <div class="navbar navbar-default mainnav">
                                        <!-- navbar-header -->
                                        <div class="navbar-header navbar-right pull-right">
                                            <!-- offcanvas-trigger-effects -->
                                            <div id="offcanvas-trigger-effects" class="column">
                                                <button type="button" class="navbar-toggle visible-sm visible-xs" data-toggle="offcanvas" data-target=".navbar-collapse" data-placement="right"  data-effect="offcanvas-effect">
                                                    <i class="fa fa-bars"></i>
                                                </button>
                                            </div> <!-- offcanvas-trigger-effects -->

                                        </div><!-- /navbar-header -->


                                        <!-- navbar-collapse -->
                                        <div class="collapse navbar-collapse">
                                            <!-- navbar-nav -->
                                            <ul class="nav navbar-nav navbar-right">

                                                <!-- Home Style -->
                                                <li class="dropdown"><a href="index.html" >Home <b class="caret"></b></a>
                                                    <!-- submenu-wrapper -->
                                                    <div class="submenu-wrapper submenu-wrapper-topbottom">
                                                        <div class="submenu-inner  submenu-inner-topbottom">
                                                            <ul class="level1 dropdown-menu">
                                                                <li><a href="index.html">Home style One</a></li>
                                                                <li><a href="index2.html">Home style Two</a></li>
                                                                <li><a href="index3.html">Home style Three</a></li>
                                                            </ul>
                                                        </div>
                                                    </div> <!-- /submenu-wrapper -->
                                                </li> <!-- /Home Style -->


                                                <!-- MEGA MENU -->
                                                <li class="dropdown mega-fw has-megamenu"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Features<b class="caret"></b></a>
                                                    <!-- megamenu-wrapper -->
                                                    <div class="submenu-wrapper submenu-wrapper-topbottom megamenu-wrapper">
                                                        <div class="submenu-inner  submenu-inner-topbottom megamenu-inner">

                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <div class="mega-content">
                                                                        <div class="row">

                                                                            <div class="col-sm-3">
                                                                                <ul class="menu-carousel">
                                                                                    <li class="dropdown-header">Latest Design</li>                            
                                                                                    <li><div id="myCarousel" class="carousel slide" data-ride="carousel">
                                                                                            <div class="carousel-inner">
                                                                                                <div class="item active">
                                                                                                    <a href="#"><img src="../img/single-promo.jpg" alt="product" ></a>
                                                                                                    <h4><small>Office Decoration</small></h4>                                        
                                                                                                    <button class="btn btn-primary btn-mega" type="button">49,99 €</button> <button  class="btn btn-primary btn-mega" type="button"><span class="glyphicon glyphicon-heart"></span> Add to Wishlist</button>       
                                                                                                </div><!-- End Item -->

                                                                                                <div class="item">
                                                                                                    <a href="#"><img src="../img/single-promo2.jpg" class="img-responsive" alt="product 2"></a>
                                                                                                    <h4><small>Home Decoration</small></h4>                                        
                                                                                                    <button class="btn btn-primary btn-mega" type="button">9,99 €</button> <button class="btn btn-primary btn-mega" type="button"><span class="glyphicon glyphicon-heart"></span> Add to Wishlist</button>        
                                                                                                </div><!-- End Item -->

                                                                                                <div class="item">
                                                                                                    <a href="#"><img src="../img/single-promo.jpg" class="img-responsive" alt="product 3"></a>
                                                                                                    <h4><small>Bedroom decoration</small></h4>                                        
                                                                                                    <button class="btn btn-primary btn-mega" type="button">49,99 €</button> <button  class="btn btn-primary btn-mega" type="button"><span class="glyphicon glyphicon-heart"></span> Add to Wishlist</button>      
                                                                                                </div><!-- End Item -->                                
                                                                                            </div><!-- End Carousel Inner -->

                                                                                            <!-- Controls -->
                                                                                            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                                                                                                <i class="fa fa-chevron-left"></i>
                                                                                            </a>

                                                                                            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                                                                                                <i class="fa fa-chevron-right"></i>
                                                                                            </a>
                                                                                        </div></li><!-- /.carousel -->

                                                                                    <li class="mega-carousel-more"><a href="#">View all Decoration <i class="fa fa-angle-right"></i></a></li>

                                                                                </ul>
                                                                            </div>


                                                                            <div class="col-sm-3">
                                                                                <ul>
                                                                                    <li class="dropdown-header">Features</li>

                                                                                    <li><a href="#">100% Responsive</a></li>
                                                                                    <li><a href="#">Mega Menu</a></li>
                                                                                    <li><a href="#">Free Supports And Update</a></li>

                                                                                    <li class="dropdown-header">Home Variation</li>

                                                                                    <li><a href="index.html">Home style One</a></li>
                                                                                    <li><a href="index2.html">Home style Two</a></li>
                                                                                    <li><a href="index3.html">Home style Three</a></li>
                                                                                </ul>
                                                                            </div>


                                                                            <div class="col-sm-3">
                                                                                <ul>
                                                                                    <li class="dropdown-header">Blog Pages</li>

                                                                                    <li><a href="blog.html">Blog Standard</a></li>
                                                                                    <li><a href="blog-single.html">Blog Single Post</a></li>

                                                                                    <li class="dropdown-header">Pages</li>

                                                                                    <li><a href="about.html">About Us</a></li>
                                                                                    <li><a href="services.html">Services</a></li>
                                                                                    <li><a href="pricing.html">Pricing</a></li>
                                                                                    <li><a href="faq.html">FAQ Page</a></li>
                                                                                </ul>
                                                                            </div>


                                                                            <div class="col-sm-3">
                                                                                <ul class="menu-form">
                                                                                    <li class="dropdown-header">Bonus Pages</li>
                                                                                    <li><a href="project.html">Project</a></li>
                                                                                    <li><a href="404.html">404 Page</a></li>
                                                                                    <li><a href="single-project.html">Single Project</a></li>
                                                                                    <li><a href="faq2.html">FAQ Page Two</a></li>

                                                                                    <li class="dropdown-header">Newsletter</li>
                                                                                    <li>
                                                                                        <form role="form">
                                                                                            <div class="form-group">
                                                                                                <label class="sr-only" for="email">Email address</label>
                                                                                                <input type="email" class="form-control" id="email" placeholder="Enter email">                                                              
                                                                                            </div>
                                                                                            <button type="submit" class="btn btn-primary btn-mega btn-block">Sign in</button>
                                                                                        </form>
                                                                                    </li>
                                                                                </ul>
                                                                            </div>								


                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div> <!-- /megamenu-wrapper -->
                                                </li> <!-- /MEGA MENU -->												

                                                <!-- Pages -->
                                                <li class="dropdown"><a href="index.html" >Pages <b class="caret"></b></a>
                                                    <!-- submenu-wrapper -->
                                                    <div class="submenu-wrapper submenu-wrapper-topbottom">
                                                        <div class="submenu-inner  submenu-inner-topbottom">
                                                            <ul class="level1 dropdown-menu">
                                                                <li><a href="about.html">About Us</a></li>
                                                                <li><a href="about2.html">About Us 2</a></li>
                                                                <li><a href="services.html">Service</a></li>
                                                                <li><a href="services2.html">Service 2</a></li>
                                                                <li><a href="project.html">Project</a></li>
                                                                <li><a href="project2.html">Project 2</a></li>
                                                                <li><a href="404.html">404 Page</a></li>
                                                                <li><a href="pricing.html">Pricing Four Col</a></li>
                                                                <li><a href="pricing2.html">Pricing Three Col</a></li>
                                                                <li><a href="pricing3.html">Pricing Two Col</a></li>
                                                                <li><a href="single-project.html">Single Project</a></li>
                                                                <li><a href="single-project-2col.html">Single Project Two</a></li>
                                                                <li><a href="single-project-fullwidth.html">Single FullWidth</a></li>
                                                                <li><a href="faq.html">FAQ Page</a></li>
                                                                <li><a href="faq2.html">FAQ Page Two</a></li>
                                                            </ul>
                                                        </div>	
                                                    </div> <!-- /submenu-wrapper -->	
                                                </li> <!-- /Pages -->

                                                <!-- Portfolio Pages -->
                                                <li class="active dropdown"><a href="#" >Portfolio <b class="caret"></b></a>
                                                    <!-- submenu-wrapper -->
                                                    <div class="submenu-wrapper submenu-wrapper-topbottom">
                                                        <div class="submenu-inner  submenu-inner-topbottom">
                                                            <ul class="level1 dropdown-menu">
                                                                <li><a href="grid-portfolio.html">Grid View</a></li>
                                                                <li class="active"><a href="portfolio.html">Four Column</a></li>
                                                                <li><a href="portfolio3.html">Three Column</a></li>
                                                                <li><a href="portfolio2.html">Two Column</a></li>
                                                            </ul>

                                                        </div>
                                                    </div> <!-- /submenu-wrapper -->
                                                </li> <!-- /Portfolio Pages -->

                                                <!-- Blog Pages -->
                                                <li class="dropdown"><a href="#" >Blog <b class="caret"></b></a>
                                                    <!-- submenu-wrapper -->
                                                    <div class="submenu-wrapper submenu-wrapper-topbottom">
                                                        <div class="submenu-inner  submenu-inner-topbottom">
                                                            <ul class="level1 dropdown-menu">
                                                                <li><a href="blog.html">Standard Blog</a></li>
                                                                <li><a href="blog-single.html">Single Blog Post</a></li>
                                                            </ul>
                                                        </div>
                                                    </div> <!-- /submenu-wrapper -->
                                                </li> <!-- /Blog Pages -->

                                                <!-- Contact Pages -->
                                                <li class="dropdown"><a href="#" >Contact <b class="caret"></b></a>
                                                    <!-- submenu-wrapper -->
                                                    <div class="submenu-wrapper submenu-wrapper-topbottom">

                                                        <div class="submenu-inner submenu-inner-topbottom">
                                                            <ul class="level1 dropdown-menu">
                                                                <li><a href="contact.html">Contact One</a></li>
                                                                <li><a href="contact2.html">Contact Two</a></li>
                                                            </ul>
                                                        </div>
                                                    </div> <!-- /submenu-wrapper -->
                                                </li> <!-- /Contact Pages -->

                                            </ul> <!-- /navbar-nav -->
                                        </div> <!-- /navbar-collapse -->
                                    </div> <!-- /navbar -->

                                </div>  <!-- /col-xs-12 -->
                            </div><!-- /row -->
                        </header><!-- /header-wrapper -->


                        <!-- Page title -->
                        <div class="page-title">
                            <h1>Portfolio</h1>

                            <!-- BREADCRUMBS -->
                            <ul class="breadcrumb">
                                <li><a href="index.html">Home</a><span class="divider"></span></li>
                                <li class="active">Portfolio</li>
                            </ul>
                        </div>
                        <!-- //Page title -->							
                    </div>
                    <!-- Project area -->
                    <section class="project">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-wrapper clearfix">
                                        <ul id="filter">
                                            <li><a class="active btn btn-primary" href="#" data-group="all">All</a></li>
                                            <li><a href="#" data-group="red" class="btn btn-primary">Home</a></li>
                                            <li><a href="#" data-group="green" class="btn btn-primary">Office</a></li>
                                            <li><a href="#" data-group="blue" class="btn btn-primary">Interior</a></li>
                                            <li><a href="#" data-group="numbers" class="btn btn-primary">Design</a></li>
                                            <li><a href="#" data-group="letters" class="btn btn-primary">Illusion</a></li>
                                            <li><a href="#" data-group="square" class="btn btn-primary">Idea</a></li>
                                            <li><a href="#" data-group="circle" class="btn btn-primary">Creative</a></li>
                                        </ul>

                                        <div id="grid">

                                            <h:form>
                                                <c:forEach items="#{products.productsItems}" var="item">
                                                    <div class="item blue col-md-3 col-sm-6 col-xs-12 fix"  data-groups='["all", "numbers", "blue", "square"]'>
                                                        <div class="project-item">
                                                            <div class="post-thumbnail element">
                                                                <img src="../img/products/demo.jpg" alt="Demo Image">
                                                                <div class="element-overly"></div>

                                                                <a data-rel="prettyPhoto" href="../img/portfolio/portfolio9.jpg" class="element-lightbox" ><i class="fa fa-search-plus"></i></a>

                                                                <a class="element-link" href="single-project.html" title=""><i class="fa fa-link"></i></a>
                                                            </div>

                                                            <div class="project-details">
                                                                <h2><a href="#"><h:outputText value="#{item.name}"/></a></h2>
                                                                <h3>$<h:outputText value="#{item.price}"/></h3>
                                                                <p><h:outputText value="#{item.infomations}"/></p>

                                                                <div class="readmore"> <h:commandLink value="Show" action="#{products.detailSetup}">
                                                                        <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][products.converter].jsfcrud_invoke}"/>
                                                                    </h:commandLink></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </c:forEach>
                                            </h:form>
                                        </div> <!-- /grid -->	
                                    </div> <!-- /box-wrapper -->

                                </div>
                            </div>
                        </div>
                    </section>
                    <!--	//shuffle  our project	-->



                    <footer class="footer-wrapper">
                        <div class="container">
                            <div class="row">

                                <div class="col-md-8">
                                    <div class="footer-contact">
                                        <span>Call Toll-Free: 1-800-559-65-80 8901</span>

                                        <span>Marmora Road Glasgow, D04 89GR</span>

                                        <span>E-mail: <a href="mailto:mail@demolink.com">mail@demolink.com </a></span>

                                    </div>
                                </div>


                                <div class="col-md-4">
                                    <div class="social-icon less-margin-top">
                                        <ul>
                                            <li><a href="#" title="Facebook" target="_blank"><i class="fa fa-facebook"> </i></a></li>

                                            <li><a href="#" title="Twitter" target="_blank"><i class="fa fa-twitter"></i></a></li>

                                            <li><a href="#" title="Google plus" target="_blank"><i class="fa fa-google-plus"></i></a></li>

                                            <li><a href="#" title="YouTube" target="_blank"><i class="fa fa-youtube"></i></a></li>

                                            <li><a href="#" title="Linkedin" target="_blank"><i class="fa fa-linkedin"></i></a></li>

                                            <li><a href="#" title="Flickr" target="_blank"><i class="fa fa-flickr"></i></a></li>
                                        </ul>

                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="footer-bottom">
                                        <div class="copyright-text">
                                            <p>Copyright © 2014 Decor. All Rights Reserved. <br> Design and Developed by Cloud Software Solution.</p>
                                            <a href="#" class="back-to-top"><i class="fa fa-angle-up backtop pull-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </footer>
                </div> <!-- end of inner wrapper -->




                <!-- offcanvas-menu -->
                <div class="offcanvas-menu offcanvas-effect">
                    <button type="button" class="close" aria-hidden="true"  data-toggle="offcanvas" id="off-canvas-close-btn" >&times;</button>

                    <h2>Sidebar Menu</h2>
                    <ul>
                        <li><a href="#">Home</a>
                            <ul>
                                <li><a href="index.html"><i class="fa fa-home"></i> Home</a></li>
                                <li><a href="index2.html"><i class="fa fa-home"></i> Home Style Tow</a></li>
                                <li><a href="index3.html"><i class="fa fa-home"></i> Home Style Three</a></li>
                            </ul>
                        </li>

                        <li><a href="#">Pages</a>
                            <ul>
                                <li><a href="about.html"><i class="fa fa-user"></i> About Us</a></li>
                                <li><a href="about2.html"><i class="fa fa-user"></i> About Us 2</a></li>
                                <li><a href="services.html"><i class="fa fa-coffee"></i> Service</a></li>
                                <li><a href="services2.html"><i class="fa fa-coffee"></i> Service 2</a></li>
                                <li><a href="project.html"><i class="fa fa-magic"></i> Project</a></li>
                                <li><a href="project2.html"><i class="fa fa-glass"></i> Project 2</a></li>
                                <li><a href="404.html"><i class="fa fa-bell-o"></i> 404 Page</a></li>
                                <li><a href="pricing.html"><i class="fa fa-puzzle-piece"></i> Pricing</a></li>
                                <li><a href="pricing2.html"><i class="fa fa-puzzle-piece"></i> Pricing Two</a></li>
                                <li><a href="single-project.html"><i class="fa fa-folder-open-o"></i> Single Project</a></li>
                                <li><a href="single-project-2col.html"><i class="fa fa-thumbs-o-up"></i>  Single Project Two</a></li>
                                <li><a href="single-project-fullwidth.html"><i class="fa fa-thumbs-o-up"></i>  Single FullWidth</a></li>
                                <li><a href="faq.html"><i class="fa fa-laptop"></i> FAQ Page</a></li>
                                <li><a href="faq2.html"><i class="fa fa-laptop"></i> FAQ Page Two</a></li>

                            </ul>
                        </li>


                        <li><a href="#" >Portfolio</a>
                            <ul>
                                <li><a href="grid-portfolio.html"><i class="fa fa-magic"></i> Grid View</a></li>
                                <li><a href="portfolio.html"><i class="fa fa-magic"></i> Four Column</a></li>
                                <li><a href="portfolio3.html"><i class="fa fa-magic"></i> Three Column</a></li>
                                <li><a href="portfolio2.html"><i class="fa fa-magic"></i> Two Column</a></li>
                            </ul>
                        </li>

                        <li><a href="#">Blog</a>
                            <ul>
                                <li><a href="blog.html"><i class="fa fa-pencil-square-o"></i> Blog Standard</a></li>
                                <li><a href="blog-single.html"><i class="fa fa-pencil-square-o"></i> Blog single post</a></li>
                            </ul>	
                        </li>

                        <li><a href="#">Contact</a>
                            <ul>
                                <li><a href="contact.html"><i class="fa fa-envelope-o"></i> Contact</a></li>
                                <li><a href="contact2.html"><i class="fa fa-envelope-o"></i> Contact Two</a></li>
                            </ul>
                        </li>
                    </ul>
                </div> <!-- /offcanvas-menu -->

            </div> <!-- /wrapper -->	



            <!-- =========================================
            JAVASCRIPT
            ========================================== -->	
            <script src="../js/jquery.min.js"></script>
            <script src="../js/bootstrap.min.js"></script>
            <script src="../js/jquery.shuffle.min.js"></script>
            <script src="../js/shuffle.js"></script>

            <!-- jQuery REVOLUTION Slider  -->
            <script type="text/javascript" src="../rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
            <script type="text/javascript" src="../rs-plugin/js/jquery.themepunch.revolution.js"></script>		
            <script src="../js/jquery.prettyPhoto.js"></script>		
            <script src="../js/jquery.donutchart.js"></script>	
            <script src="../js/owl.carousel.min.js"></script>
            <script src="../js/sidebarEffects.js"></script>
            <script src="../js/classie.js"></script>
            <script src="../js/smoothscroll.min.js"></script>			
            <script src="../js/script.js"></script>
        </body>
    </html>
</f:view>
