
package takingnotes;

public class NoteTakingApp {
   
        public NoteTakingApp() {
            start();
        }
        private void start()
        {
           FileManager mainFile = new FileManager(Path.mainPath);
            Login login = new Login();
        }
        
        public static void main(String[] args) throws Exception{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 new NoteTakingApp();
            }
        });
        
        }
}


