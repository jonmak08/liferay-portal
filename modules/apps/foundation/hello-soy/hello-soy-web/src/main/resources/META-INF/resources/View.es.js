import Component from 'metal-component/src/Component';
<<<<<<< HEAD
import Footer from './Footer.soy';
import Header from './Header.soy';
import Soy from 'metal-soy/src/Soy';
import templates from './View.soy';

class View extends Component {}
=======
import Footer from './Footer.es';
import Header from './Header.es';
import Soy from 'metal-soy/src/Soy';
import templates from './View.soy';

class View extends Component {
	constructor(opt_config) {
		super(opt_config);
	}
}
>>>>>>> compatible

// Register component
Soy.register(View, templates);

export default View;