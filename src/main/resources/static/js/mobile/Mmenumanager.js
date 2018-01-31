var M_MENUMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询菜单
     */
    M_MENUMANAGER.querymenus = function () {
        DEFAULT.Ajax("/m/menu/queryMenus", null, false, function (res) {
            if (res.result == true) {
                var managerMenus = eval('(' + res.msg + ')');
                for (var i = 0; i < managerMenus.length; i++) {

                    var oneHtml = "<li onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                        "<i class='fa fa-newspaper-o'></i>"+
                        "<p>"+managerMenus[i].name+"</p>" +
                        "</li>";
                    $("#menu").append(oneHtml);

                    i++;
                    if (managerMenus.length != i) {
                        var twoHtml = "<li class='borders' onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                            "<i class='fa fa-archive'></i>"+
                            "<p>"+managerMenus[i].name+"</p>" +
                            "</li>";
                        $("#menu").append(twoHtml);
                    }

                    i++;
                    if (managerMenus.length != i) {
                        var twoHtml = "<li onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                            "<i class='fa fa-book'></i>"+
                            "<p>"+managerMenus[i].name+"</p>" +
                            "</li>";
                        $("#menu").append(twoHtml);
                    }

                    i++;
                    var oneHtml = "<li onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                        "<i class='fa fa-cloud'></i>"+
                        "<p>"+managerMenus[i].name+"</p>" +
                        "</li>";
                    $("#menu").append(oneHtml);

                    i++;
                    if (managerMenus.length != i) {
                        var twoHtml = "<li class='borders' onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                            "<i class='fa fa-cube'></i>"+
                            "<p>"+managerMenus[i].name+"</p>" +
                            "</li>";
                        $("#menu").append(twoHtml);
                    }

                    i++;
                    if (managerMenus.length != i) {
                        var twoHtml = "<li onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                            "<i class='fa fa-desktop'></i>"+
                            "<p>"+managerMenus[i].name+"</p>" +
                            "</li>";
                        $("#menu").append(twoHtml);
                    }

                    i++;
                    var oneHtml = "<li onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                        "<i class='fa fa-folder-open'></i>"+
                        "<p>"+managerMenus[i].name+"</p>" +
                        "</li>";
                    $("#menu").append(oneHtml);

                    i++;
                    if (managerMenus.length != i) {
                        var twoHtml = "<li class='borders' onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                            "<i class='fa fa-gear'></i>"+
                            "<p>"+managerMenus[i].name+"</p>" +
                            "</li>";
                        $("#menu").append(twoHtml);
                    }

                    i++;
                    if (managerMenus.length != i) {
                        var twoHtml = "<li onclick='javascript:M_MENUMANAGER.toWebManager("+ managerMenus[i].id + ")'>" +
                            "<i class='fa fa-server'></i>"+
                            "<p>"+managerMenus[i].name+"</p>" +
                            "</li>";
                        $("#menu").append(twoHtml);
                    }
                }
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