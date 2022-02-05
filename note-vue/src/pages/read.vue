<template>
   <div id="read">

      <div class="poemDiv" v-if="poems != undefined || poems != null" v-for="poem in poems">
         
         <PoemCard :poem= poem />
        
      </div>

   </div>

</template>

<script>
import PoemCard from '../components/poemCard.vue'

export default {
  name:"read",
   data () {
      return {
         poems:[],
      };
   }, 
   components: {PoemCard},

   created(){
      let that = this
         this.$axios({
            url:"/note/getAll",
            method:"GET",
         }).then((result)=>{
            that.poems = JSON.parse(result.data.message)
         }).catch((err)=>{
            console.log("err log:"+err);
            alert("err log:"+err)
         })
   },
}

</script>
<style lang='css' scoped>

#read{
   height: 90%;
   width: 100%;
}

.poemDiv{
   width: 90%;
   margin: 0 auto;
}

</style>