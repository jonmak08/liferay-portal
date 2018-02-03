import Component from 'metal-component/src/Component';
<<<<<<< HEAD
import Footer from 'hello-soy-web/Footer.soy';
import Header from 'hello-soy-web/Header.soy';
import Soy from 'metal-soy/src/Soy';
import templates from './Navigation.soy';

class Navigation extends Component {}
=======
import Footer from './Footer.es';
import Header from './Header.es';
import Soy from 'metal-soy/src/Soy';
import templates from './Navigation.soy';

class Navigation extends Component {
	constructor(opt_config) {
		super(opt_config);
	}
}
>>>>>>> compatible

// Register component
Soy.register(Navigation, templates);

export default Navigation;