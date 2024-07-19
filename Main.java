import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("kaçlık matris? nxn n: ");

        int n = sc.nextInt();
        double[][] matris = new double[n][n + 1];

        // matrisi doldur
        System.out.println("matris değerlerini girin");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ".satır değerlerini gir");
            for (int j = 0; j < n + 1; j++) {
                matris[i][j] = sc.nextDouble();
            }
        }

        // matris köşegenlerini ayarla
        for (int i = 0; i < n; i++) {

            int coordinateX = 0;
            boolean status = false;
            //int coordinateY = 0;
            double[] temporary = new double[n + 1];
            double maxValue = matris[i][i];

            for (int j = i + 1; j < n; j++) {
                if (maxValue < matris[j][i]) {
                    maxValue = matris[j][i];
                    coordinateX = j;
                    //coordinateY = i;
                    status = true;
                }
            }
            for (int j = 0; j < n + 1 && status; j++) {
                temporary[j] = matris[i][j];
                matris[i][j] = matris[coordinateX][j];
                matris[coordinateX][j] = temporary[j];
            }
        }
        // matrisi görüntüle
        showMatris(n,matris);

        double[] unknownValues = new double[n];
        double tolerance = 0;
        double[] lastValues = new double[n];
        System.out.print("Hata payını gir % ");
        tolerance = sc.nextDouble();
        boolean isResultFound = false;

        for (int a = 0;!isResultFound && a<1000&&n!=0; a++) {
            System.out.println("******* " + (a + 1) + ". iterasyon *******");
            for (int i = 0; i < n; i++) {
                double total = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) total += (-matris[i][j] * unknownValues[j]);
                }
                unknownValues[i] = total;
                unknownValues[i] = (1.0 / matris[i][i]) * (matris[i][n] + unknownValues[i]);
            }
            for (int i = 0; i < n && a>0; i++) {
                double lastAndCurrentValueDifferent =  Math.abs(unknownValues[i] - lastValues[i]);
                if(lastAndCurrentValueDifferent< (tolerance/100.0)){
                    if (i==(n-1)){
                        System.out.println((a+1)+".İterasyonda"+" %"+tolerance+" hata payı ile aşağıdaki kökler bulundu");
                        isResultFound = true;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                lastValues[j] = unknownValues[j];
                System.out.println("x" + (j + 1) + ": " + unknownValues[j]);
            }
            System.out.println("=========================");

        }

    }
    private static void showMatris(int n,double matris[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print((j == 0 ? " | " : "") + matris[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
