<footer class="site-footer">
  <div class="container">
    <div class="footer-content col-md-12">

    </div>
    <!-- .footer-content .col-md-10 .col-md-offset-1 -->

    <div class="col-md-12 text-center social-media">
      <ul class="social-media-list">

        <#if ${socialMediaOne}>
        <li>
          <a href="${socialMediaOneLink}"><span class="${socialMediaOne} social-media-icon" aria-hidden="true"></span></a>
        </li>
        </#if>

        <#if ${socialMediaTwo}
        <li>
          <a href="${socialMediaTwoLink}"><span class="${socialMediaTwo} social-media-icon" aria-hidden="true"></span></a>
        </li>
        </#if>

        <#if ${socialMediaThree}
        <li>
          <a href="${socialMediaThreeLink}"><span class="${socialMediaThree} social-media-icon" aria-hidden="true"></span></a>
        </li>
        </#if>

        <#if ${socialMediaFour}
        <li>
          <a href="${socialMediaFourLink}"><span class="${socialMediaFour} social-media-icon" aria-hidden="true"></span></a>
        </li>
        </#if>

        <#if ${socialMediaFive}
        <li>
          <a href="${socialMediaFiveLink}"><span class="${socialMediaFive} social-media-icon" aria-hidden="true"></span></a>
        </li>
        </#if>

      </ul>
     </div>
     <!-- .col-md-12 .text-center -->

    <div class="col-md-12 text-center contact-info">
      <span class="contact-info">${contactInfo}</span>
    </div>
    <!-- .col-md-12 .text-center .contact-info -->

    <div class="col-md-12 text-center site-info">
      <a href="#"><span>${siteInfo}</span></a>
    </div>
    <!-- .col-md-12 .text-center .site-info -->
  </div>
  <!-- .container -->
</footer>
<!-- .site-footer -->
