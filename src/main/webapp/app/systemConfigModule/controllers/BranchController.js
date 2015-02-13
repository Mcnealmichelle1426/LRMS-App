/**
 * Created by kelly on 10/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('BranchController',BranchController);

    BranchController.$inject = ['lodash','BranchService'];

    function BranchController(_,BranchService){
        var me = this;
        me.branch = {};
        me.getAllBranchController = getAllBranchController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.branches = [];
        me.deactivateItem = deactivateItem;
        me.exampleData = null;

        me.branches = [
            {
                id:1,
                active:true,
                code:'code 1',
                name:"name 1",
                postalAddress:"address postal",
                postalCode:" pss125",
                location:{id:"1",name:"loation 1"},
                branchHead:{id:'1',name:"branch"},
                description:'description'
            },{
                id:2,
                active:false,
                code:'code 2',
                name:"name 2",
                postalAddress:"address postal",
                postalCode:" pss125",
                location:{id:"1",name:"loation 1"},
                branchHead:{id:'1',name:"branch"},
                description:'description'
            },{
                id:3,
                active:false,
                code:'code 3',
                name:"name 3",
                postalAddress:"address postal",
                postalCode:" pss125",
                location:{id:"1",name:"loation 1"},
                branchHead:{id:'1',name:"branch"},
                description:'description'
            },{
                id:4,
                active:true,
                code:'code 4',
                name:"name 4",
                postalAddress:"address postal",
                postalCode:" pss125",
                location:{id:"1",name:"loation 1"},
                branchHead:{id:'1',name:"branch"},
                description:'description'
            },{
                id:5,
                active:true,
                code:'code 5',
                name:"name 5",
                postalAddress:"address postal",
                postalCode:" pss125",
                location:{id:"1",name:"loation 1"},
                branchHead:{id:'1',name:"branch"},
                description:'description'
            }
        ];

        getAllBranchController();

        function getAllBranchController(){
            console.log(_.isArray(me.branches));
            //BranchService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});
            var number = new Bloodhound({
                datumTokenizer:function(d){return Bloodhound.tokenizers.whitespace(d.num);},
                queryTokenizer:Bloodhound.tokenizers.whitespace,
                local:[
                    {num:'one'},
                    {num:'two'},
                    {num:'three'},
                    {num:'four'}
                ]
            });

            number.initialize();

            me.dataSets = {
                highlight:true
            }
            me.exampleData = {
                displayKey:'num',
                source:number.ttAdapter()
            }


        }
        function saveChanges(){
            //console.log(me.branch);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.branch.id){
                BranchService.editBranch(me.branch, me.branch.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.branches,{id:me.branch.id});
                    if(index > -1){
                        _.extend(me.branches[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.branches.push(me.branch);
                BranchService.saveBranch(me.branch).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }
        }
        function editData(data){
            me.branch =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.branches,{id:data.id});
            if(index > -1){
                me.branches.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.branch = {};
        }
    }
})();


