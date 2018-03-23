import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

class WebServer extends Thread {
    Socket s;

    public static void main(String args[])
    {
        try
        {
            // что такое backlog?
            ServerSocket server = new ServerSocket(8000, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("server is started");
            while(true)
            {
                WebServer ws = new WebServer(server.accept());
                ws.start();
            }
        }
        catch(Exception e)
        {System.out.println("init error: " + e);}
    }

    public WebServer(Socket s){
        this.s = s;
    }

    public void run()
    {
        try
        {
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

//            // буффер данных в 64 килобайта
//            byte buf[] = new byte[64*1024];
//            // читаем 64кб от клиента, результат - кол-во реально принятых данных
//            int r = is.read(buf);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
            String request = bfr.readLine();
            // получаем путь до документа
            String path = getPath(request);
            // если из запроса не удалось выделить путь, то
            // возвращаем "400 Bad Request"
            if(path == null)
            {
                // первая строка ответа
                String response = "HTTP/1.1 400 Bad Request\n";

                // дата в GMT
                DateFormat df = DateFormat.getTimeInstance();
                df.setTimeZone(TimeZone.getTimeZone("GMT"));
                response = response + "Date: " + df.format(new Date()) + "\n";

                // остальные заголовки
                response = response
                        + "Connection: close\n"
                        + "Server: SimpleWEBServer\n"
                        + "Pragma: no-cache\n\n";

                //! выводим данные:
                os.write(response.getBytes());
                s.close();
                return;
            }


            // если файл существует и является директорией,
            // то ищем index.html
            File f = new File(path);
            boolean flag = !f.exists();
            if(!flag)
                if(f.isDirectory()) {
                if(path.lastIndexOf("" + File.separator) == path.length() - 1)
                    path = path + "index.html";
                else
                    path = path + File.separator + "index.html";
                f = new File(path);
                flag = !f.exists();
            }

            // если по указанному пути файл не найден
            // то выводим ошибку "404 Not Found"
            if(flag)
            {
                // первая строка ответа
                String response = "HTTP/1.1 404 Not Found\n";

                // дата в GMT
                DateFormat df = DateFormat.getTimeInstance();
                df.setTimeZone(TimeZone.getTimeZone("GMT"));
                response = response + "Date: " + df.format(new Date()) + "\n";

                // остальные заголовки
                response = response
                        + "Content-Type: text/plain\n"
                        + "Connection: close\n"
                        + "Server: SimpleWEBServer\n"
                        + "Pragma: no-cache\n\n";

                response = response + "File " + path + " not found!";
                os.write(response.getBytes());
                s.close();
                return;
            }

            // определяем MIME файла по расширению
            // MIME по умолчанию - "text/plain"
            String mime = "text/plain";
            // выделяем у файла расширение (по точке)
            int r = path.lastIndexOf(".");
            if(r > 0)
            {
                String ext = path.substring(r);
                if(ext.equalsIgnoreCase("html"))
                    mime = "text/html";
                else if(ext.equalsIgnoreCase("htm"))
                    mime = "text/html";
                else if(ext.equalsIgnoreCase("gif"))
                    mime = "image/gif";
                else if(ext.equalsIgnoreCase("jpg"))
                    mime = "image/jpeg";
                else if(ext.equalsIgnoreCase("jpeg"))
                    mime = "image/jpeg";
                else if(ext.equalsIgnoreCase("bmp"))
                    mime = "image/x-xbitmap";
            }

            // создаём ответ

            // первая строка ответа
            String response = "HTTP/1.1 200 OK\n";

            // дата создания в GMT
            DateFormat df = DateFormat.getTimeInstance();
            df.setTimeZone(TimeZone.getTimeZone("GMT"));

            // время последней модификации файла в GMT
            response = response + "Last-Modified: " + df.format(new Date(f.lastModified())) + "\n";

            // длина файла
            response = response + "Content-Length: " + f.length() + "\n";

            // строка с MIME кодировкой
            response = response + "Content-Type: " + mime + "\n";

            // остальные заголовки
            response = response
                    + "Connection: close\n"
                    + "Server: SimpleWEBServer\n\n";

            // выводим заголовок:
            os.write(response.getBytes());

//            // и сам файл:
//            FileInputStream fis = new FileInputStream(path);
//            r = 1;
//            while(r > 0)
//            {
//                r = fis.read(buf);
//                if(r > 0) os.write(buf, 0, r);
//            }
//            fis.close();

            // завершаем соединение
            s.close();
        }
        catch(Exception e)
        {e.printStackTrace();} // вывод исключений
    }


    // "вырезает" из HTTP заголовка URI ресурса и конвертирует его в filepath
    // URI берётся только для GET и POST запросов, иначе возвращается null
    protected String getPath(String header)
    {
        // ищем URI, указанный в HTTP запросе
        // URI ищется только для методов POST и GET, иначе возвращается null
        String URI = extract(header, "GET ", " "), path;
        if(URI == null) URI = extract(header, "POST ", " ");
        if(URI == null) return null;

        // если URI записан вместе с именем протокола
        // то удаляем протокол и имя хоста
        path = URI.toLowerCase();
        if(path.indexOf("http://", 0) == 0)
        {
            URI = URI.substring(7);
            URI = URI.substring(URI.indexOf("/", 0));
        }
        else if(path.indexOf("/", 0) == 0)
            URI = URI.substring(1); // если URI начинается с символа /, удаляем его

        // отсекаем из URI часть запроса, идущего после символов ? и #
        int i = URI.indexOf("?");
        if(i > 0) URI = URI.substring(0, i);
        i = URI.indexOf("#");
        if(i > 0) URI = URI.substring(0, i);

        // конвертируем URI в путь до документов
        // предполагается, что документы лежат там же, где и сервер
        // иначе ниже нужно переопределить path
        path = "." + File.separator;
        char a;
        for(i = 0; i < URI.length(); i++)
        {
            a = URI.charAt(i);
            if(a == '/')
                path = path + File.separator;
            else
                path = path + a;
        }

        return path;
    }


    // "вырезает" из строки str часть, находящуюся между строками start и end
    // если строки end нет, то берётся строка после start
    // если кусок не найден, возвращается null
    // для поиска берётся строка до "\n\n" или "\r\n\r\n", если таковые присутствуют
    protected String extract(String str, String start, String end)
    {
        int s = str.indexOf("\n\n", 0), e;
        if(s < 0) s = str.indexOf("\r\n\r\n", 0);
        if(s > 0) str = str.substring(0, s);
        s = str.indexOf(start, 0)+start.length();
        if(s < start.length()) return null;
        e = str.indexOf(end, s);
        if(e < 0) e = str.length();
        return (str.substring(s, e)).trim();
    }
}