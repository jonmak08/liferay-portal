
document.addEventListener("DOMContentLoaded", function () {
    drop_navigation();
});



const drop_navigation = () => {
    const dropdown_element_nav = document.getElementsByClassName("nav__dropdown");


    for (let index = 0; index < dropdown_element_nav.length; index++) {
        let current_dropdown = dropdown_element_nav[index];


        current_dropdown.onclick = () => {
            debugger;

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

