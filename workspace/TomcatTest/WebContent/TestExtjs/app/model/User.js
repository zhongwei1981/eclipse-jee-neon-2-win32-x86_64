Ext.define('AM.model.User', {
	extend : 'Ext.data.Model',
	fields : [ 'name', 'email' ],
	
	// Proxies are the way to load and save data from a Store or a Model.
	// Different proxies for AJAX: JSON-P, HTML5 localStorage
	proxy : {
		type : 'ajax',
		api : {
			read: '/TomcatTest/UsersServlet',
			update: '/TomcatTest/UsersServlet'
		},
		// The reader is responsible for decoding the server response into a
		// format the Store can understand
		reader : {
			type : 'json',
			root : 'users',
			successProperty : 'success',
			messageProperty : 'message'
		},

		writer: {
			writeAllFields: true,
		    nameProperty: 'mapping'
		},
		
		listeners : {
			exception : function(reader, response, error, eOpts) {
				if (response && response.responseText) {
					Ext.Msg.alert('Server error: ', response.responseText);
				}
			}
		}
	}
});