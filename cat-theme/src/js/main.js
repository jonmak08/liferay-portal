
// **** DOCUMENT READY *****//
const callback = () => {
    drop_nav();
    truncate();
  };
  
  if (
    document.readyState === "complete" ||
    (document.readyState !== "loading" && !document.documentElement.doScroll)
  ) {
    callback();
  
  } else {
    document.addEventListener("DOMContentLoaded", callback);
  }


  
  // **** NAVIGATION *****//
  
  const nav_dropdown_el = document.getElementsByClassName("nav__dropdown");
  
  const drop_nav = () => {
    for (let index = 0; index < nav_dropdown_el.length; index++) {
  
      let current_dropdown = nav_dropdown_el[index];
  
      current_dropdown.onclick = () => {
  
        const target_nested_list = document.querySelector(`#dropdown0${index + 1}`);
  
        if (target_nested_list.classList.contains("collapsed")) {
  
          const all_dropdown = document.querySelectorAll('.nav__list--stacked');
  
          all_dropdown.forEach(element => {
            element.classList.add("collapsed")
          });
  
          target_nested_list.classList.remove("collapsed");
  
        } else {
  
  
          target_nested_list.classList.add("collapsed");
        }
      }
    }
  }
  
  
  // **** CONTENT *****//
  
  
  const descriptions = document.querySelectorAll('.truncate_desc');
  
  const truncate = () => {
    Array.prototype.forEach.call(descriptions, (description) => {
      const p_height = description.querySelector('p');
      const div_height = description.clientHeight;
      while (p_height.offsetHeight > div_height) {
        p_height.textContent = p_height.textContent.replace(/\W*\s(\S)*$/, '...');
      }
    })
  }
  
  
  