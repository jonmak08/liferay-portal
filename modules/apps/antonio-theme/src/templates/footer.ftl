<footer class="site-footer">
  <div class="container">

    <#if addFooterSection>
      <section class="footer-content col-md-12">
        <div class="footer-section text-center">
          <h3 class="footer-section-title">${footerSectionTitle}</h3>
          <!-- .footer-section-title -->
          <p class="footer-section-entry col-md-6 col-md-offset-3">
            ${footerSectionEntry}
          </p>
          <!-- .footer-section-entry -->
        </div>
        <!-- .footer-section -->
      </section>
      <!-- .footer-content .col-md-12 -->
    </#if>

    <div class="col-md-12 text-center social-media">
      <ul class="social-media-list">

        <#if socialMediaOne != "">
          <li>
            <a href="${socialMediaOneLink}" target="_blank"><span class="${socialMediaOne} social-media-icon" aria-hidden="true"></span></a>
          </li>
        </#if>

        <#if socialMediaTwo !="">
          <li>
            <a href="${socialMediaTwoLink}" target="_blank"><span class="${socialMediaTwo} social-media-icon" aria-hidden="true"></span></a>
          </li>
        </#if>

        <#if socialMediaThree !="">
          <li>
            <a href="${socialMediaThreeLink}" target="_blank"><span class="${socialMediaThree} social-media-icon" aria-hidden="true"></span></a>
          </li>
        </#if>

        <#if socialMediaFour !="">
          <li>
            <a href="${socialMediaFourLink}" target="_blank"><span class="${socialMediaFour} social-media-icon" aria-hidden="true"></span></a>
          </li>
        </#if>

        <#if socialMediaFive !="">
          <li>
            <a href="${socialMediaFiveLink}" target="_blank"><span class="${socialMediaFive} social-media-icon" aria-hidden="true"></span></a>
          </li>
        </#if>

      </ul>
     </div>
     <!-- .col-md-12 .text-center -->

    <div class="col-md-12 text-center">
      <span class="contact-info">
        <@liferay.language key="powered-by" /> <a href="http://www.liferay.com" target="_blank" rel="external">Liferay</a>
      </span>
    </div>
    <!-- .col-md-12 .text-center .site-info -->
  </div>
  <!-- .container -->
</footer>
<!-- .site-footer -->
