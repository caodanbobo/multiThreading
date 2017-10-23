package test;

import java.util.*;

/**
 * Created by zhenboliu on 2017/5/16.
 */
public class Test {

    static int cnt=0;

    public static void main(String[] args){
        int[][] edges=new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        //findMinHeightTrees(4,edges);

        int[] arr = {-1,0,1,2,-1,-4};
        //containsNearbyAlmostDuplicate(arr,2,1);
        threeSum(arr);

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        findTriplets(0,3,new ArrayList<Integer>(), ans, nums, 0);
        return ans;
    }
    private static void findTriplets(int start, int left, List<Integer> list, List<List<Integer>> ans, int[] nums, int sum){
        if(sum>0 || start>=nums.length) return;
        else if(left==0 && sum==0){
            ans.add(new ArrayList<Integer>(list));
            return;
        }else if(left==0) return;
        int pre=nums[start]-1;
        for(int i=start; i<nums.length-left+1;i++){
            if(nums[i]!=pre){
                pre=nums[i];
                list.add(nums[i]);
                findTriplets(i+1,left-1,list,ans,nums,sum+nums[i]);
                list.remove(list.size()-1);
            }
        }
    }


    public static boolean isAdditiveNumber(String num) {
        if(num.length()<3) return false;
        int n=num.length();
        char[] strs=num.toCharArray();
        for(int i=0;i<n/2;i++){
            for(int j=i+1;j<n;j++){
                if(helper(strs, 0, i, j)) return true;
            }
        }
        return false;
    }
    private static boolean helper(char[] str,int start, int i, int j){
        if(i!=start && str[start]=='0') return false;
        if(j-i>1 && str[i+1]=='0') return false;
        int left=str.length-j-1;
        if(left<i-start+1 || left<j-i) return false;
        StringBuilder sb=new StringBuilder();
        int k=i,g=j;
        int carry=0;
        while(k>=start || g>i || carry!=0){
            int n1=k>=start?str[k--]-'0':0;
            int n2=g>i?str[g--]-'0':0;
            int sum=n1+n2+carry;
            carry=sum/10;
            sum=sum%10;
            sb.append(sum);
        }
        sb=sb.reverse();
        System.out.println(sb);
        if(str.length-j-1<sb.length()) return false;
        for(int l=0;l<sb.length();l++){
            if(sb.charAt(l)!=str[j+1+l]) return false;
        }
        if(j+sb.length()==str.length-1) return true;
        return helper(str, i+1,j,j+sb.length());
    }


    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length<2 || k<1 || t<0) return false;
        PriorityQueue<Integer> min=new PriorityQueue<>((p1,p2)->(p1-p2));
        for(int i=0;i<nums.length;i++){
            min.offer(nums[i]);
            if(min.size()>k+1){
                Integer remove=nums[i-k-1];
                min.remove(remove);
            }
            int pre=min.peek()-t-1;
            for(int n:min){
                if(n-pre<=t) return true;
                pre=n;
            }
        }
        return false;
    }

    public static void quickSort(int[] arr, int lo, int hi){
        if(lo<hi){
           int pi=partition(arr, lo,hi);
            quickSort(arr, lo,pi-1);
            quickSort(arr,pi+1,hi);
        }

    }
    private static int partition(int[] arr, int l, int r){
        int pivot=arr[r];
        int i=l-1;
        for(int j=l;j<r;j++,cnt++){
            if(arr[j]<=pivot){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,r);
        return i+1;
    }
    private static void swap(int[] arr, int i, int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }



}
