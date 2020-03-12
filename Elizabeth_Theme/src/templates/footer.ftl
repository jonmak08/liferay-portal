<footer id="footer" role="contentinfo">
  <div class="container">
    <div class="row">
      <#-- FOOTER - About Us Section -->
      <div class="col-sm-4">
        <h5 class="section_footer_heading">
          <span class="theme-color-text">About Us</span>
        </h5>
        <div>
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti dolorum, 
          sint corporis nostrum, possimus unde eos vitae eius quasi saepe.
        </div>
      </div>
      <#--  FOOTER - Contact Info Section  -->
      <div class="col-sm-4">
        <h5 class="section_footer_heading"><span class="theme-color-text">Contact Info</span></h5>
          <div id="contact">
            <ul class="nav flex-column mx-auto">
              <li class="mx-2">
                  <div id="facebook">
                    <a class="text-white" target="_blank">
                      <@clay["icon"] symbol="geolocation" />
                      1400 Montefino Ave, Diamond Bar, CA 91765
                    </a>
                  </div>
              </li>            
              <li class="mx-2">
                  <div id="facebook">
                    <a class="text-white" target="_blank">
                      <@clay["icon"] symbol="phone" />
                      (877) 543-3729
                    </a>
                  </div>
              </li>
              <li class="mx-2">
                <div id="facebook">
                  <a class="text-white" target="_blank">
                    <@clay["icon"] symbol="envelope-closed" />
                    <a href="mailto:life.ray@liferay.com">life.ray@liferay.com</a>
                  </a>
                </div>
              </li>
            </ul>                 
          </div>
      </div>
      <#--  FOOTER - Opening Hours section  -->
      <div class="col-sm-4">
        <h5 class="section_footer_heading">
          <span class="theme-color-text">
            Opening Hours
          </span>
        </h5>
        <p>
          Monday-Thursday<br>
          10:00 AM - 11:00 PM <br>
          Friday - Sunday<br>
          12:00AM - 3:00 AM <br>
        </p>
      </div>
    </div>

  <#--  <div class="text-center mx-auto">
    <div class="nav text-uppercase" role="menubar">
      <#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />

      <@liferay.navigation_menu
        default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
        instance_id="footer_navigation_menu"
      />
    </div>
  </div>  -->
</footer>