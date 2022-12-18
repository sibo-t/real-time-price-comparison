import controllers.ItemsController;
import io.javalin.Javalin;


public class Server {
    public static final int SERVER_PORT = 9000;

    private Javalin server;


    public static void main( String[] args ){
        final Server svr = new Server().initialise();
        svr.start();
    }

    Server initialise(){
        server = configureHttpServer();
        return this;
    }

    public void start(){
        run();
    }

    public void stop(){
        server.stop();
    }

    public void run(){
        server.start( SERVER_PORT );
    }

    private Javalin configureHttpServer(){
        server = Javalin.create()
                .post("/item", ItemsController::processItem)
                .get("/item", ItemsController::viewItem)
                .get("/", ctx -> ctx.result("This will be the landing"));
        return server;
    }


}
