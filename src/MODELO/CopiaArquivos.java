package MODELO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class CopiaArquivos {

    /**
     * Copia arquivos de um local para o outro.
     *
     * @param origem - Arquivo de origem
     * @param destino - Arquivo de destino
     * @param overwrite - Confirmação para sobrescrever os arquivos
     * @throws IOException
     */
    public static void copy(File origem, File destino, boolean overwrite) throws IOException {
        if (destino.exists() && !overwrite) {
            return;
        }
        FileInputStream source = new FileInputStream(origem);
        FileOutputStream destination = new FileOutputStream(destino);
        FileChannel sourceFileChannel = source.getChannel();
        FileChannel destinationFileChannel = destination.getChannel();
        long size = sourceFileChannel.size();
        sourceFileChannel.transferTo(0, size, destinationFileChannel);
    }

    /**
     * Copia todos os arquivos que tenham uma determinada extensão de uma pasta
     * de origem para outra de destino.
     *
     * @param origem - Diretório onde estão os arquivos a serem copiados
     * @param destino - Diretório onde os arquivos serão copiados
     * @param extensao - <i>String</i> Extensão do arquivo que deve ser copiada.
     * Você pode inserir a extensão no formato: ".doc" ou "doc" (por exemplo)
     * @param overwrite - Confirmação para sobrescrever os arquivos
     * @throws IOException, UnsupportedOperationException
     */
    public void copyAll(File origem, File destino, String extensao, boolean overwrite) throws IOException, UnsupportedOperationException {

        if (!destino.exists()) {
            destino.mkdir();
        }
        if (!origem.isDirectory()) {
            throw new UnsupportedOperationException("Origem deve ser um diretório");
        }
        if (!destino.isDirectory()) {
            throw new UnsupportedOperationException("Destino deve ser um diretório");
        }
        File[] files = origem.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
                copyAll(files[i], new File(destino + "\\" + files[i].getName()), overwrite);
            } else if (files[i].getName().endsWith(extensao)) {
                copy(files[i], new File(destino + "\\" + files[i].getName()), overwrite);
            }
        }
    }

    /**
     * Copia todos os arquivos de dentro de uma pasta para outra.
     *
     * @param origem - Diretório onde estão os arquivos a serem copiados
     * @param destino - Diretório onde os arquivos serão copiados
     * @param overwrite - Confirmação para sobrescrever os arquivos
     * @throws IOException, UnsupportedOperationException
     */
    public void copyAll(File origem, File destino, boolean overwrite) throws IOException, UnsupportedOperationException {
        String arquivos = null;
        if (!destino.isDirectory()) {

        }
        if (!destino.exists()) {
            destino.mkdir();
        }

        File[] files = origem.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
                copyAll(files[i], new File(destino + "\\" + files[i].getName()), overwrite);

            } else {
                arquivos = " " + files[i].getName();
                copy(files[i], new File(destino + "\\" + files[i].getName()), overwrite);

            }

            /// JOptionPane.showMessageDialog(null, " Arquivos!!" + files[i]);
        }
    }

    public void copiaMapaIndice(File ImgSelecionada, String nomeImagem, String caminhoImagem, boolean overwrite) throws IOException {

        FileInputStream origem;
        FileOutputStream destino;
        FileChannel fcOrigem;
        FileChannel fcDestino;
        File novoDiretorio = new File(caminhoImagem + " - (" + nomeImagem + ")");

        if (!novoDiretorio.exists()) {
            if (novoDiretorio.mkdir()) { //cria o diretório "caelum-copia" e o diretório "Java" dentro da pasta /home/
                JOptionPane.showMessageDialog(null, "Novo diretorio criado em " + novoDiretorio.getAbsolutePath());

                origem = new FileInputStream(ImgSelecionada);//arquivo que você quer copiar
                destino = new FileOutputStream(novoDiretorio + "\\" + nomeImagem);//onde irá ficar a copia do aquivo
                fcOrigem = origem.getChannel();
                fcDestino = destino.getChannel();
                fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);//copiando o arquivo e salvando no diretório que você escolheu
                JOptionPane.showMessageDialog(null, "Arquivo copiado " + nomeImagem + " para " + novoDiretorio.getAbsolutePath());
                origem.close();
                destino.close();
            }

        }

    }

//    public void copiaFotografiaAerea(File ImgSelecionada, String nomeImagem, String caminhoImagem) throws IOException {
//
//        FileInputStream origem;
//        FileOutputStream destino;
//        FileChannel fcOrigem;
//        FileChannel fcDestino;
//
//        File novoDiretorio = new File(caminhoImagem +"-" +nomeImagem);
//
//        if (!novoDiretorio.exists()) {
//            if (novoDiretorio.mkdir()) {; //cria o diretório "caelum-copia" e o diretório "Java" dentro da pasta /home/
//                JOptionPane.showMessageDialog(null, "Novo diretorio criado em " + novoDiretorio.getAbsolutePath());
//
//                origem = new FileInputStream(ImgSelecionada);//arquivo que você quer copiar
//                destino = new FileOutputStream(novoDiretorio + "\\" + nomeImagem);//onde irá ficar a copia do aquivo
//                fcOrigem = origem.getChannel();
//                fcDestino = destino.getChannel();
//                fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);//copiando o arquivo e salvando no diretório que você escolheu
//                JOptionPane.showMessageDialog(null, "Arquivo copiado " + nomeImagem + " para " + novoDiretorio.getAbsolutePath());
//                origem.close();
//                destino.close();
//            }
//
//        }
//
//    }
//
//    public void addUmArquivo(File ImgSelecionada, String nomeImagem, String caminhoBD) throws IOException {
//
//        FileInputStream origem;
//        FileOutputStream destino;
//        FileChannel fcOrigem;
//        FileChannel fcDestino;
//
//        origem = new FileInputStream(ImgSelecionada);//arquivo que você quer copiar
//        destino = new FileOutputStream(caminhoBD + "\\" + nomeImagem);//onde irá ficar a copia do aquivo
//        fcOrigem = origem.getChannel();
//        fcDestino = destino.getChannel();
//
//        origem.close();
//        destino.close();
//    }
    public void copiaFotografiaAerea(File ImgSelecionada, String nomeImagem, String caminhoImagem, boolean overwrite) throws IOException {

        FileInputStream origem;
        FileOutputStream destino;
        FileChannel fcOrigem;
        FileChannel fcDestino;
        File novoDiretorio = new File(caminhoImagem);

        if (!novoDiretorio.exists()) {
            if (novoDiretorio.mkdir()) { //cria o diretório "caelum-copia" e o diretório "Java" dentro da pasta /home/
                JOptionPane.showMessageDialog(null, "Novo diretorio criado em " + novoDiretorio.getAbsolutePath());

                origem = new FileInputStream(ImgSelecionada);//arquivo que você quer copiar
                destino = new FileOutputStream(novoDiretorio + "\\" + nomeImagem);//onde irá ficar a copia do aquivo
                fcOrigem = origem.getChannel();
                fcDestino = destino.getChannel();
                fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);//copiando o arquivo e salvando no diretório que você escolheu
                JOptionPane.showMessageDialog(null, "Arquivo copiado " + nomeImagem + " para " + novoDiretorio.getAbsolutePath());
                origem.close();
                destino.close();
            }

        }

    }

    public void copyFileUsingStream(File imagemSelecioada,String localDestino) throws IOException {
       FileInputStream origem; 
       FileOutputStream destino;
       FileChannel fcOrigem;
       FileChannel fcDestino;
       origem = new FileInputStream(imagemSelecioada);//arquivo que você quer copiar
       JOptionPane.showMessageDialog(null, imagemSelecioada);
       destino = new FileOutputStream(localDestino+"\\"+imagemSelecioada.getName());//onde irá ficar a copia do aquivo
        fcOrigem = origem.getChannel();
        fcDestino = destino.getChannel();
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);//copiando o arquivo e salvando no diretório que você escolheu
        origem.close();
        destino.close();    
    }
    
    
    // testes copiando


}
