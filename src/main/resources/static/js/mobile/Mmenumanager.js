var M_MENUMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询菜单
     */
    M_MENUMANAGER.querymenus = function () {
        DEFAULT.Ajax("/m/menu/queryMenus", null, false, function (res) {
            if (res.result == true) {
                var managerMenus = eval('(' + res.msg + ')');
                var html = "";
                for (var i = 0; i < managerMenus.length; i++) {
                    html += "<div class='ui-grid-a' >" +
                        "            <div class='ui-block-a'>" +
                        "<a href='javascript:M_MENUMANAGER.toWebManager(" + managerMenus[i].id + ")'" +
                        "                   class='ui-shadow ui-btn ui-corner-all ui-mini ui-icon-home ui-btn-icon-top'>" + managerMenus[i].name + "</a>" +
                        "            </div>";
                    i++;
                    if (managerMenus.length != i) {
                        html += "<div class='ui-block-b'>" +
                            "<a href='javascript:M_MENUMANAGER.toWebManager(" + managerMenus[i].id + ")'" +
                            "                   class='ui-shadow ui-btn ui-corner-all ui-mini ui-icon-home ui-btn-icon-top'>" + managerMenus[i].name + "</a>" +
                            "            </div>";
                    }
                    html += "</div>";
                }
                $("#menu").append(html);
            }
        })
    };

    /**
     * 查询内容
     * @param id
     */
    M_MENUMANAGER.toWebManager = function (id) {
        window.location.href = "/m/webmanager/?id=" + id;
    };

})();