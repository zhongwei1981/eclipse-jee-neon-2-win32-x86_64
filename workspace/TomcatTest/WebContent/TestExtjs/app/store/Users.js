Ext.define('AM.store.Users', {
	extend : 'Ext.data.Store',
	model : 'AM.model.User',
	autoLoad : true, // the Store will ask its Proxy to load that data
						// immediately

	// Proxies are the way to load and save data from a Store or a Model.
	// Different proxies for AJAX: JSON-P, HTML5 localStorage
	proxy : {
		type : 'ajax',
		api : {
			read : 'data/users.json',
			update : 'data/updateUsers.json'
		},
		// The reader is responsible for decoding the server response into a
		// format the Store can understand
		reader : {
			type : 'json',
			root : 'users',
			successProperty : 'success'
		}
	},

	sorters : [ {
		property : 'name',
		direction : 'DESC'
	} ]
});