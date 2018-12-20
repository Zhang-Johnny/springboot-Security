package org.sang;


//import org.junit.Test;
//import org.sang.common.SnowFlake;
//import org.sang.common.Utils;
//import org.sang.common.tool.AuxiliaryTool;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.concurrent.*;

//@Transactional
public class test {
//   private final AuxiliaryTool auxiliaryTool=new AuxiliaryTool();
//   public int x=0;
//   @Test
//    public void test1() {
//        ThreadPoolExecutor executor=new ThreadPoolExecutor(50,80,10, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
//        String[] vad={"XhxDeliveryMailingAddress","XhxDepartment","XhxFreeTrlalRecord","XhxGroupUser","XhxInvoiceAptitude","XhxInvoiceOrder"};
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(++x);
//            }
//        });
//        int[] cg={1,2,5,8,10,15,16,54,69,100};
//        Long id = SnowFlake.getSnowFlakeId("XhxInvoiceOrder");//先生成发票订单id
//       HashMap<String,Object> hashMap=new HashMap<>();
//
//   }
//    public static int binarySearch(int[] arr,int x){
//       int low=0;
//       int high=arr.length-1;
//       while (low<=high){
//           int middle=(low+high)/2;
//           if(x==arr[middle]){
//               return middle;
//           }else if(x<arr[middle]){
//               high=middle-1;
//           }else {
//               high=middle+1;
//           }
//       }
//       return -1;
//    }
//         //递归实现二分查找
//                 public static int binarySearch(int[] dataset,int data,int beginIndex,int endIndex){
//                   int midIndex = (beginIndex+endIndex)/2;
//                    if(data <dataset[beginIndex]||data>dataset[endIndex]||beginIndex>endIndex){
//                            return -1;
//                        }
//                    if(data <dataset[midIndex]){
//                            return binarySearch(dataset,data,beginIndex,midIndex-1);
//                        }else if(data>dataset[midIndex]){
//                           return binarySearch(dataset,data,midIndex+1,endIndex);
//                        }else {
//                            return midIndex;
//                       }
//                }
//
//             public static void main(String[] args) {
///*                 int[] arr = { 6, 12, 33, 87, 90, 97, 108, 561 };
//                 System.out.println("循环查找：" + (binarySearch(arr, 87) + 1));
//                 System.out.println("递归查找"+binarySearch(arr,3,87,arr.length-1));*/
//                 ThreadPoolExecutor executor=new ThreadPoolExecutor(50,80,10, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
//                for(int i=0;i<10000;i++){
//                    executor.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            Long id = SnowFlake.getSnowFlakeId("XhxInvoiceOrder");//先生成发票订单id
//                            System.out.println("线程名称："+Thread.currentThread().getName()+":ID长度是"+id.toString().length()+"|||"+id);
//                        }
//                    });
//                }
//             }
}
