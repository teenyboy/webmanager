var M_MENUMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询菜单
     */
    M_MENUMANAGER.querymenus = function () {
        DEFAULT.ajax("/mmenu/queryMenus",null,false,function (res) {
            if (res.result == true) {
                var managerMenus = eval('('+res.msg+')');
                for(var i = 0;i<managerMenus.size();i++){
                    var html = "<div class='ui-grid-a' >" +
                        "            <div class='ui-block-a'>" +
                        "                <input type='button'" +
                        "                   class='ui-shadow ui-btn ui-corner-all ui-mini ui-icon-home ui-btn-icon-top' onclick=M_MENUMANAGER.toContent('"+managerMenus.get(i).id+"')>"+managerMenus.get(i).name+"</input>" +
                        "            </div>" ;
                    i++;
                    if(managerMenus.size() != i){
                        html += "<div class='ui-block-b'>" +
                        "                <input type='button'" +
                        "                   class='ui-shadow ui-btn ui-corner-all ui-mini ui-icon-info ui-btn-icon-top' onclick=M_MENUMANAGER.toContent('"+managerMenus.get(i).id+"')>"+managerMenus.get(i+1).name+"</input>" +
                        "            </div>";
                    }
                    html +="</div>";
                }
                $("#menu").append(html);
            }
        })
    };

    /**
     * 查询内容
     * @param id
     */
    M_MENUMANAGER.toContent = function (id) {
        window.location.href = "/m/webmanager/toContent?id="+id;
    };

})();