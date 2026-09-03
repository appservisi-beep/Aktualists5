import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Work {

    public static void main(String Args[]){


    }

    public String zamanbul(String ilktarih,String sontarih){
        Date date=new Date();
        SimpleDateFormat tarihFormat=new SimpleDateFormat("dd.M.yyyy");
        String simdikitarih=tarihFormat.format(date);

                String zaman="boş";
        if(ilktarih!="" || sontarih!=""){

            try {
                Date simdikizaman=tarihFormat.parse(simdikitarih);
                Date ilkzaman=tarihFormat.parse(ilktarih);
                Date sonzaman=tarihFormat.parse(sontarih);
                long longsimdikiZaman=simdikizaman.getTime();
                long longilkZaman=ilkzaman.getTime();
                long longsonZaman=sonzaman.getTime();

                if(longilkZaman>longsimdikiZaman){

                    int gun= (int) ((longilkZaman-longsimdikiZaman)/(1000*60*60*24));
                    zaman="Başlamaya "+gun+" gün kaldı!";

                }else if(longilkZaman==longsimdikiZaman){

                    zaman="Bugün Başladı";
                }else if(longsonZaman>longsimdikiZaman){

                    int gun= (int) ((longsonZaman-longsimdikiZaman)/(1000*60*60*24));
                    zaman=gun+" gün kaldı!";
                }else{

                    zaman="Süresi Doldu";
                }




            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


        return zaman;
    }


}
