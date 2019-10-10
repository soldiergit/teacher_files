layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/match_findByPage.action',//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            // {field: 'matchId', title: 'Id', align:'center'},
            {field: 'matchName', title: '赛事名称', minWidth:200, align:"center"},
            {field: 'matchLevel', title: '赛事级别', minWidth:50, align:'center', templet:function(d){
                    if (d.matchLevel=='1') return '国家级';
                    if (d.matchLevel=='2') return '省级';
                }},
            {field: 'matchType', title: '赛事类型', minWidth:50, align:'center', templet:function(d){
                    if (d.matchType=='1') return '学科竞赛';
                    if (d.matchType=='2') return '教学竞赛';
                    if (d.matchType=='3') return '创新创业';
                    if (d.matchType=='4') return '其它';
                }},
            {field: 'organizer', title: '主办单位', minWidth:100, align:'center'},
            {field: 'contractor', title: '承办单位', minWidth:100, align:'center'},
            {field: 'teacherName', title: '负责人', minWidth:100, align:'center', templet:function(d){
                    return d.teacher.teacherName;
                }},
            {field: 'deptName', title: '所在部门', minWidth:100, align:'center', templet:function(d){
                    return d.teacher.dept.deptName;
                }},
            {title: '操作', minWidth:200, templet:'#infoListBar',fixed:"right",align:"center"}
        ]]
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {key: dataReload.val()}
            }, 'data');
        }
    };

    $('.layui-card-body .reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $(".addBtn").click(function(){
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("/teacher_files_war/biz/match_delete.action",{
                    matchId : data.matchId  //参数
                },function(data){
                    if (data.code === 0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("有学生或老师参加了该赛事，禁止删除！");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }else if(layEvent === 'detail'){ //详情
            detailUser(data);
        }
    });

    //批量删除
    $(".delBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].matchId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher_files_war/biz/match_deleteBatch.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("有学生或老师参加了该赛事，禁止删除！");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加",
            type : 2,
            content : "match-add.html",
            maxmin: true,
            area: ['800px', '600px'],
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.matchId);
                    body.find(".matchName").val(edit.matchName).prop("disabled", true);
                    body.find(".matchLevel").val(edit.matchLevel);
                    body.find(".matchType").val(edit.matchType);
                    // body.find(".matchAttribute").val(edit.matchAttribute);
                    body.find(".organizer").val(edit.organizer);
                    body.find(".contractor").val(edit.contractor);
                    body.find(".teacherHide").val(edit.teacher.teacherId);//使用中间变量记录赛事负责人
                    body.find(".updateFlag").val(1);//更新

                    //回显示文件
                    $.ajax({
                        type: "POST",
                        url: '/teacher_files_war/biz/matchAnnex_findByMatchId.action',//数据接口
                        data: {matchId: edit.matchId},
                        success: function (data) {
                            if (data.code === 0) {
                                $.each(data.data, function (index, item) {
                                    var oldtr = $(['<tr id="upload-'+ index +'">',
                                        '<td>'+ item.fileName +'</td>',
                                        // '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
                                        '<td></td>',
                                        '<td>已上传</td>',
                                        '<td>',
                                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                                        '</td>',
                                        '</tr>'].join(''));

                                    //删除
                                    oldtr.find('.demo-delete').on('click', function(){
                                        $.post("/teacher_files_war/biz/matchAnnex_delete.action", {
                                            matchAnnexId: item.matchAnnexId //将需要删除的matchAnnexId作为参数传入
                                        }, function (data) {
                                            if (data.code === 0) {
                                                layer.msg("删除成功");
                                            } else {
                                                layer.msg("删除失败");
                                            }
                                            tableIns.reload();
                                            layer.close(index);
                                        });
                                        oldtr.remove();//删除当前
                                    });

                                    body.find("#demoList").append(oldtr);
                                });
                            } else {
                                layer.msg("未知错误，请联系管理员！" + data.msg);
                            }

                        },
                        error: function () {
                            layer.msg("可能是因为网络原因操作失败了，请重试，若多次重试不成功，请于网站管理员联系");
                        }
                    });

                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //用户详情
    function detailUser(data) {
        var index = layui.layer.open({
            title: "详情",
            type: 2,
            content: "match-detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);

                body.find(".matchId").val(data.matchId);
                body.find(".matchName").val(data.matchName);
                body.find(".matchLevel").val(data.matchLevel);
                body.find(".matchType").val(data.matchType);
                // body.find(".matchAttribute").val(data.matchAttribute);
                body.find(".organizer").val(data.organizer);
                body.find(".contractor").val(data.contractor);
                body.find(".teacherName").val(data.teacher.teacherName);
                body.find(".deptName").val(data.teacher.dept.deptName);
                var str = "";   //附件
                $.ajax({
                    type: "POST",
                    url: '/teacher_files_war/biz/matchAnnex_findByMatchId.action',//数据接口
                    data: {matchId: data.matchId},
                    success: function (data) {
                        if (data.code === 0) {
                            $.each(data.data, function (index, item) {
                                str += '<a href="/teacher_files_war/download/download_downloadFile.action?' +
                                    'downloadPath=' + item.filePath + '&filename=' + item.fileName + '">' + item.fileName + '</a><br>';
                                // 'downloadPath=' + item.path + '&filename=《' + data.paperName + '》附件' + index + '">《' + data.paperName + '》附件' + index + '</a><br>';
                            });
                            body.find("#manyFile").html(str);
                        }
                }});

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