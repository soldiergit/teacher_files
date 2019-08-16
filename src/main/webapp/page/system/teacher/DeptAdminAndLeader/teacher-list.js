layui.use(['form','layer','table','laytpl','util'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        util = layui.util,
        table = layui.table;

    var deptId = window.sessionStorage.getItem("deptId");
    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/teacher_findByDept.action?deptId='+deptId,//数据接口
        cellMinWidth : 95,
        page: true,//开启分页
        height: "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id: "infoListTable",
        cols: [[//表头
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'teacherCode', title: '教师工号', minWidth: 100, align: "center"},
            {field: 'teacherName', title: '教师姓名', minWidth: 100, align: 'center'},
            {field: 'entryTime', title: '入职时间', minWidth: 100, align: 'center', templet: function (d) {
                    return util.toDateString(d.entryTime, "yyyy-MM-dd")
                }},
            {field: 'deptName', title: '所属部门', minWidth: 100, align: 'center', templet: function (d) {
                    return d.dept.deptName;
                }},
            {field: 'teacherSex', title: '性别', minWidth: 100, align: 'center', templet: function (d) {
                    return d.teacherSex == '1' ? '男' : '女';}},
            {field: 'teacherBirth', title: '出生年月', minWidth: 100, align: 'center', templet: function (d) {
                    return util.toDateString(d.teacherBirth, "yyyy-MM-dd")
                }},
            {field: 'highEdu', title: '最高学历', minWidth: 100, align: 'center'},
            {field: 'firstEdu', title: '第一学历', minWidth: 100, align: 'center'},
            {field: 'technicalPost', title: '专业技术职务', minWidth: 100, align: 'center'},
            {field: 'administPost', title: '行政职务', minWidth: 100, align: 'center'},
            // {field: 'teacherResume', title: '简历', minWidth: 100, align: 'center'},
            {field: 'other', title: '主要从事工作', minWidth: 100, align: 'center'},
            {title: '操作', minWidth: 175, templet: '#infoListBar', fixed: "right", align: "center"}
        ]]
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: dataReload.val()
                }
            }, 'data');
        }
    };
    $('.layui-card-body .reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $(".addBtn").click(function () {
        addUser();
    });

    //批量删除
    $(".delBtn").click(function () {
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].teacherId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher_files_war/biz/teacher_deleteBatch.action", {
                    ids: newsId.join(',') //将需要删除的newsId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的教师");
        }
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/teacher_files_war/biz/teacher_delete.action", {
                    teacherId: data.teacherId  //将需要删除的newsId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        } else if (layEvent === 'detail') {
            detailUser(data);
        }
    });

    //添加教师
    function addUser(edit) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "../teacher-add.html",
            maxmin: true,
            area: ['800px', '800px'],
            btn: ['确定', '取消'],
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".Id").val(edit.teacherId);
                    body.find(".teacherCode").val(edit.teacherCode).prop("disabled", true);
                    body.find(".teacherName").val(edit.teacherName);
                    body.find(".roleName").val(edit.roleName);
                    body.find(".entryTime").val(util.toDateString(edit.entryTime, "yyyy-MM-dd"));
                    body.find(".teacherBirth").val(util.toDateString(edit.teacherBirth, "yyyy-MM-dd"));
                    body.find(".teacherImg").val(edit.teacherImg);
                    body.find("#imgPreview").attr('src', edit.teacherImg);//图片显示
                    body.find(".highEdu").val(edit.highEdu);
                    body.find(".firstEdu").val(edit.firstEdu);
                    body.find(".technicalPost").val(edit.technicalPost);
                    body.find(".administPost").val(edit.administPost);
                    body.find(".teacherResume").val(edit.teacherResume);
                    body.find(".other").val(edit.other);
                    body.find(".teacherSex input[value=" + edit.teacherSex + "]").prop("checked", "checked");  //性别单选框选中
                    body.find(".deptHide").val(edit.dept.deptId);  //使用中间变量保存部门id
                    body.find(".unitHide").val(edit.unitIds);  //使用中间变量保存所属教研室
                    body.find(".roleHide").val(edit.roleIds);  //使用中间变量保存角色id
                    body.find(".updateFlag").val(1);
                    form.render();
                } else {
                    body.find(".deptHide").val(window.sessionStorage.getItem("deptId"));  //使用中间变量保存部门id
                }
                body.find("#deptId").prop("disabled", true);  //部门不许修改
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        // layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    };

    //教师详情
    function detailUser(data) {
        var index = layui.layer.open({
            title: "详情",
            type: 2,
            content: "../teacher-detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);

                //基本信息
                body.find(".teacherCode").val(data.teacherCode);
                body.find(".teacherName").val(data.teacherName);
                body.find(".deptName").val(data.dept.deptName);
                body.find(".roleName").val(data.roleNames);
                body.find(".teacherSex").val(data.teacherSex===1 ? '男' : '女');
                body.find(".teacherBirth").val(util.toDateString(data.teacherBirth, "yyyy年MM月dd日"));
                body.find(".entryTime").val(util.toDateString(data.entryTime, "yyyy年MM月dd日"));
                body.find(".teacherImg").val(data.teacherImg);
                body.find(".imgPreview").attr('src', data.teacherImg);//图片显示
                body.find(".highEdu").val(data.highEdu);
                body.find(".firstEdu").val(data.firstEdu);
                body.find(".technicalPost").val(data.technicalPost);
                body.find(".administPost").val(data.administPost);
                body.find(".teacherResume").val(data.teacherResume);
                body.find(".other").val(data.other);
                body.find(".unitHide").val(data.unitIds);  //使用中间变量保存所属教研室

                //其他信息
                body.find(".thisTeacherId").val(data.teacherId);

                form.render();
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
});