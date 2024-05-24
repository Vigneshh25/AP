package Problems.set9;

class RightGreater {
    public int[] replaceElements(int[] arr) {
        if(arr.length==0) return arr;
        int max = arr[arr.length-1];
        arr[arr.length-1]=-1;

        for(int i=arr.length-2; i>=0; i--){
            if(arr[i]> max){
                int m=arr[i];
                arr[i]=max;
                max=m;
            }
            else{
                arr[i]=max;
            }
        }
        System.gc(); 
        return arr;
    }
}
