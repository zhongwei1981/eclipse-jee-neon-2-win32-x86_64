Ext.define('AM.store.Users', {
	extend : 'Ext.data.Store',
	model : 'AM.model.User',
	autoLoad : true, // the Store will ask its Proxy to load that data
	// immediately

	filters : [ {
		property : 'name',
		value : /Ed/
	} ],

	sorters : [ {
		property : 'name',
		direction : 'DESC'
	} ]
});