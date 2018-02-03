var M_WEBMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询内容
     * @param menuid
     */
    M_WEBMANAGER.queryWebManager = function (menuid) {
        var data = {menuid:menuid};
        DEFAULT.Ajax("/m/webmanager/queryWebManager",data,false,function (res) {
            if (res.result == true) {
                var webManagers = eval('(' + res.msg + ')');

                if(webManagers.length === 0){
                    $("#managerView").append("<label style='margin-top: 30px;text-align: center'>暂无内容~~~</label>");
                    return;
                }

                for(var i = 0;i<webManagers.length;i++){
                    var html ='<li><a href="javascript:M_WEBMANAGER.toContent('+webManagers[i].id+')"><p>'+webManagers[i].title+'</p></a></li>';
                    $("#webManagerList").append(html);
                }
                $('#webManagerList').listview('refresh');
            }else{
                alert(res.msg);
            }

        })
    };

    /**
     * 内容展示
     * @param id
     */
    M_WEBMANAGER.toContent = function (id) {
        window.location.href = "/m/webcontent/?webMid="+id;
    }

})();