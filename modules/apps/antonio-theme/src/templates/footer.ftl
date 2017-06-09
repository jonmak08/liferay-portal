<footer class="site-footer">
  <div class="container">

    <#if addFooterSection>
      <section class="col-md-12 footer-content">
        <div class="footer-section text-center">
          <h3 class="footer-section-title">${footerSectionTitle}</h3>
          <!-- .footer-section-title -->
          <p class="col-md-6 col-md-offset-3 footer-section-entry">
            ${footerSectionEntry}
          </p>
          <!-- .footer-section-entry -->
        </div>
        <!-- .footer-section -->
      </section>
      <!-- .footer-content .col-md-12 -->
    </#if>

    <div class="col-md-12 social-media text-center">
      <ul class="social-media-list">

        <#if socialMediaOne != "">
          <li>
            <a aria-hidden="true" href="${socialMediaOneLink}" target="_blank"><span class="${socialMediaOne} social-media-icon"></span></a>
          </li>
        </#if>

        <#if socialMediaTwo !="">
          <li>
            <a aria-hidden="true" href="${socialMediaTwoLink}" target="_blank"><span class="${socialMediaTwo} social-media-icon"></span></a>
          </li>
        </#if>

        <#if socialMediaThree !="">
          <li>
            <a aria-hidden="true" href="${socialMediaThreeLink}" target="_blank"><span class="${socialMediaThree} social-media-icon"></span></a>
          </li>
        </#if>

        <#if socialMediaFour !="">
          <li>
            <a aria-hidden="true" href="${socialMediaFourLink}" target="_blank"><span class="${socialMediaFour} social-media-icon"></span></a>
          </li>
        </#if>

        <#if socialMediaFive !="">
          <li>
            <a aria-hidden="true" href="${socialMediaFiveLink}" target="_blank"><span class="${socialMediaFive} social-media-icon"></span></a>
          </li>
        </#if>

      </ul>
     </div>
     <!-- .col-md-12 .text-center -->

    <div class="col-md-12 text-center">
      <span class="contact-info">
        <@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external" target="_blank">Liferay</a>
      </span>
    </div>
    <!-- .col-md-12 .text-center .site-info -->
  </div>
  <!-- .container -->
</footer>
<!-- .site-footer -->
