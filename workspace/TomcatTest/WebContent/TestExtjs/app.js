// See file:///F:/zhong_J2EE/ext-4.2.1.883/docs/index.html#!/guide/application_architecture
// For console.log('#### xxx'), it is logged into Chrome->F12->Console
Ext.application({
	requires : [ 'Ext.container.Viewport' ],
	name : 'AM',

	appFolder : 'app',

	controllers : [ 'Users' ],

	launch : function() {
		Ext.create('Ext.container.Viewport', {
			layout : 'fit',
			items : [ {
				xtype : 'userlist'
			} ]
		});
	}
});
