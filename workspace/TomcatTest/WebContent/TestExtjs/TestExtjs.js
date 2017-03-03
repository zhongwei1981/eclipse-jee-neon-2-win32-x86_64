Ext.require([ 'Ext.data.*', 'Ext.grid.*' ]);

Ext.onReady(function() {
	console.log("#### 111");

	Ext.define('Book', {
		extend : 'Ext.data.Model',
		proxy : {
			type : 'ajax',
			reader : 'xml'
		},
		fields : [
		// set up the fields mapping into the xml doc
		// The first needs mapping, the others are very basic
		{
			name : 'Author',
			mapping : '@author.name'
		}, 'Title', 'Manufacturer', 'ProductGroup' ]
	});

	// create the Data Store
	var store = Ext.create('Ext.data.Store', {
		model : 'Book',
		autoLoad : true,
		sorters : [ {
			property : 'Title',
			direction : 'ASC'
		} ],

		// load using HTTP
		proxy : new Ext.data.proxy.Ajax({
			url : '/HelloServlet'
		})
		/*
		 * proxy: { type: 'ajax', // load using HTTP url: 'XMLData.xml', reader: { //
		 * the return will be XML, so lets set up a reader type: 'xml', record:
		 * 'Item', // records will have an "Item" tag idProperty: 'ASIN',
		 * totalRecords: '@total' } }
		 */
	});
	
	var operation = new Ext.data.Operation({
	    action: 'read'
	});
	
	store.proxy.read(operation);

	// create the grid
	Ext.create('Ext.grid.Panel', {
		store : store,
		columns : [ {
			text : "Author",
			flex : 1,
			dataIndex : 'Author'
		}, {
			text : "Title",
			width : 180,
			dataIndex : 'Title'
		}, {
			text : "Manufacturer",
			width : 115,
			dataIndex : 'Manufacturer'
		}, {
			text : "Product Group",
			width : 100,
			dataIndex : 'ProductGroup'
		} ],
		renderTo : 'example-grid',
		width : 540,
		height : 200
	});
});
