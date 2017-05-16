Ext.define('AM.controller.Users', {
	extend : 'Ext.app.Controller',

	models : [ 'User' ],

	stores : [ 'Users' ],

	views : [ 'user.List', 'user.Edit' ],	//to load these files automatically

	init : function() {
		console.log('#### Initialized Users! This happens before the Application launch function is called');
		this.control({
			// About ComponentQuery, see file:///F:/zhong_J2EE/ext-4.2.1.883/docs/index.html#!/api/Ext.ComponentQuery
			'viewport > panel' : {
				//Event: operation
				render : this.onPanelRendered
			},
			'viewport > userlist' : {
				itemdblclick : this.editUser
			},
			'useredit button[action=save]' : {	// 'useredit' = xtype, 'save' = action
				click : this.updateUser
			}
		});
	},

	onPanelRendered : function() {
		console.log('#### The panel was rendered');
	},

	editUser : function(grid, record) {
		console.log('#### Double clicked on ' + record.get('name'));

		var view = Ext.widget('useredit');	// = Ext.create('widget.useredit')
		view.down('form').loadRecord(record);
	},

	updateUser: function(button) {
        console.log('#### clicked the Save button');

        var win    = button.up('window'),
	        form   = win.down('form'),
	        record = form.getRecord(),
	        values = form.getValues();

	    record.set(values);
	    win.close();
	    // synchronize the store after editing the record
	    this.getUsersStore().sync();
    }
});
