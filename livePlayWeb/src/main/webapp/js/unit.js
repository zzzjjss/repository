function stringIsEmpty(str){
	if(str==null||str==undefined||str==""){
		return true;
	}else{
		return str.replace(/(^\s*)|(\s*$)/g, "").length ==0
	}
}