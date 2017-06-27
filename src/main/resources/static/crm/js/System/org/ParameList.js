$(function() {
	var option = {
		theme : 'vsStyle',
		expandLevel : 10,
		beforeExpand : function($treeTable, id) {
			//判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
			if ($('.' + id, $treeTable).length) {
				return;
			}
			//这里的html可以是ajax请求
			// alert(id);
			$.ajax({
				url : 'parameListByPid',
				type : 'get',
				data : {
					"pid" : id
				},
				success : function(result) {
					//alert(result);
					$treeTable.addChilds(result);
				}
			});
		},
		onSelect : function($treeTable, id) {
			window.console && console.log('onSelect:' + id);
		}
	};
	$('#treeTable1').treeTable(option);

	/*   option.theme = 'default';
	  $('#treeTable2').treeTable(option); */
});