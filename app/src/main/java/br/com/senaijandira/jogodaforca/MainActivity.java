package br.com.senaijandira.jogodaforca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    // Declarando os TextView
    TextView txtPalavra, txtLetras, txtDicas;

    // Declarando um ImageView
    ImageView imgForca;

    // Declarando um MediaPlayer
    MediaPlayer mediaPlayer;

    // Declarando um AlertDialog
    AlertDialog alerta;

    // Declarando um Drawable
    Drawable drawable;

    // Declarando os Button
    Button btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ, btnDica;

    // Vetor que recebe todas as palavras do jogo
    String palavra[][] = {
            {"Celta", "Fiesta", "Civic", "Sandero", "Hilux", "Fox", "Corolla", "Golf", "Azera", "Palio"},
            {"Abacaxi", "Laranja", "Uva", "Goiaba", "Morango", "Banana", "Manga", "Melancia", "Kiwi", "Tangerina"},
            {"Cachorro", "Cobra", "Gato", "Vaca", "Tartaruga", "Ovelha", "Cavalo", "Macaco", "Papagaio", "Lobo"},
            {"Corinthians", "Palmeiras", "Flamengo", "Cruzeiro", "Vasco", "Internacional", "Fluminense", "Chapecoense", "Botafogo", "Bahia"},
            {"Salvador", "Campinas", "Jandira", "Fortaleza", "Curitiba", "Natal", "Osasco", "Recife", "Santos", "Guarulhos"}
    };

    // Vetor que recebe todas as dicas das palavras
    String dica[] = {"Carro", "Fruta", "Animal", "Time", "Cidade"};

    // Declarando um Random
    Random random;

    // Declarando os int
    int qtdPalavras, indiceSorteio, qtdDicas, indiceDica, acertos, compararAcertos;

    // chancesRestantes vai sempre ser iniciada em 6
    int chancesRestantes = 6;

    // Declarando uma String para receber a palavra do vetor
    String palavraSorteada;

    // Declarando uma StringBuilder para esconder  palavra
    StringBuilder palavraEscondida = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pegando os ID do xml
        txtPalavra = findViewById(R.id.txtPalavra);
        txtLetras = findViewById(R.id.txtLetras);
        txtDicas = findViewById(R.id.txtDica);
        imgForca = findViewById(R.id.imgForca);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnE = findViewById(R.id.btnE);
        btnF = findViewById(R.id.btnF);
        btnG = findViewById(R.id.btnG);
        btnH = findViewById(R.id.btnH);
        btnI = findViewById(R.id.btnI);
        btnJ = findViewById(R.id.btnJ);
        btnK = findViewById(R.id.btnK);
        btnL = findViewById(R.id.btnL);
        btnM = findViewById(R.id.btnM);
        btnN = findViewById(R.id.btnN);
        btnO = findViewById(R.id.btnO);
        btnP = findViewById(R.id.btnP);
        btnQ = findViewById(R.id.btnQ);
        btnR = findViewById(R.id.btnR);
        btnS = findViewById(R.id.btnS);
        btnT = findViewById(R.id.btnT);
        btnU = findViewById(R.id.btnU);
        btnV = findViewById(R.id.btnV);
        btnW = findViewById(R.id.btnW);
        btnX = findViewById(R.id.btnX);
        btnY = findViewById(R.id.btnY);
        btnZ = findViewById(R.id.btnZ);
        btnDica = findViewById(R.id.btnDica);

        // Chamando a método que inicia o jogo
        iniciarJogo();

    }

    // Método que inicia o jogo
     public void iniciarJogo(){

        // Chamando método que troca de imagem
         mudarImagem();

         // Criando um Random
        random = new Random();

        // qtdDicas recebe o tamanho do vetor
        qtdDicas = dica.length;

        // indiceDica recebe um indice sorteado do vetor dicas
        indiceDica = random.nextInt(qtdDicas);

        // qtdPalavras recebe o tamanho das colunas da matriz
        qtdPalavras = palavra[0].length;

        // indiceSorteio recebe um indice sorteado do vetor
        indiceSorteio = random.nextInt(qtdPalavras);

        // palavraSorteada recebe a palavra referente a linha com indiceDica, coluna com indiceSorteio e com todas as letras maiúsculas
        palavraSorteada = palavra[indiceDica][indiceSorteio].toUpperCase();

        // Loop que conta até o tamanho da palavra sorteada
        for (int i = 0; i <palavraSorteada.length(); i++){

            // A cada volta do loop, acrescenta um "_ " na palavraEscondida através do método append que concatena strings
            palavraEscondida.append("_ ");

        }

        // Exibindo na tela as letras escondidas da palavra
         txtPalavra.setText(palavraEscondida.toString());

        // Exibindo a quantidade de letras na palavra
         txtLetras.setText(palavraSorteada.length() + " letras");

        // Criando o MediaPLayer
         mediaPlayer = MediaPlayer.create(this, R.raw.mission_impossible);

         // Método para música não parar
         mediaPlayer.setLooping(true);

        // Iniciando a música
         mediaPlayer.start();

    }

    // Método para pausar a música ao quando sair, porém não fechar o app
    @Override
    protected void onPause() {
        super.onPause();

        // Parar música
        mediaPlayer.pause();
    }

    // Método para música voltar do mesmo lugar que parou ao pausar
    @Override
    protected void onResume() {
        super.onResume();

        // Verificar se a música está parada e começar a tocar de novo
        if(!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }

    // Método que é chamado com o clique do botao DICA
    public  void mostraDica (View view){

        // Exibindo a dica referente ao indice sorteado
        txtDicas.setText("Dica: " + dica[indiceDica]);

        // Após o click, o botão fica verde e não será possível clicar de novo
        btnDica.setEnabled(false);
        btnDica.setBackgroundResource(R.color.verde);

    }

    // Método que é chamado com o clique dos botões de A-Z
    public void VerificarLetra (View view) {

        // botao recebe a view que foi clicada na tela
        Button botao = (Button) view;

        // letra recebe o texto que está no botão clicado
        String letra = botao.getText().toString();


        // compararAcertos recebe acertos
        compararAcertos = acertos;

        // Loop que conta até o tamanho da palavra sorteada
        for (int i = 0; i < palavraSorteada.length(); i++) {

            // Verefica se tem a letra clicada na palavra
            if (letra.equals(String.valueOf(palavraSorteada.charAt(i)))) {

                // Se tiver, troca o "_" pela letra digitada e acrescenta 1 nos acertos
                // i vai ser sempre * 2 para ignorar os espaços em palavraEscondida ao fazer a contagem
                palavraEscondida.setCharAt(i*2, letra.charAt(0));
                acertos += 1 ;

            }
        }

        // Verifica se acertos tem o mesmo valor que compararAcertos após o loop
        if (acertos == compararAcertos){

            // Se tiver, subtrai 1 das chances que restam
            chancesRestantes--;

            // Deixar o botão vermelho se a letra estiver errada
            botao.setEnabled(false);
            botao.setBackgroundResource(R.color.red);

        }
        else {

            // Deixar o botão verde se a letra estiver certa
            botao.setEnabled(false);
            botao.setBackgroundResource(R.color.verde);

        }

        // Exibe a palavra escondida que vai atualizando
        txtPalavra.setText(palavraEscondida.toString());

        // Verifica se o número de acertos é igual o tamanho da palavra
        if (acertos == palavraSorteada.length()){

            // Se for, chama a função que exibe a vitória no jogo
            alert("VOCÊ VENCEU", "Parabéns pela vitória!");

           // Verifica se as chances restantes é igual a 0
        } else if (chancesRestantes == 0){

            // Se for, chama a função que exibe a derrota no jogo
            alert("VOCE PERDEU", "Sua palavra era: " + palavraSorteada);

        }

        // Chamando o método para mudar a imagem
        mudarImagem();

    }

    // Método para criar um AlertDialog
    public void alert(String titulo, String mensagem){

        // Cria um gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Definindo um título
        builder.setTitle(titulo);

        // Definindo uma mensagem
        builder.setMessage(mensagem);

        // Não deixa a pessoa clicar fora do enquanto o alert enquanto o alert não for fechado
        builder.setCancelable(false);

        // Se clicado, o jogo é encerrado
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });


        // Se clicado, o jogo reinicia
        builder.setPositiveButton("Jogar novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reiniciarJogo();
            }
        });

        // Cria o AlertDialog
        alerta = builder.create();

        // Exibe o alert
        alerta.show();

        // Parar a música quando finalizar o jogo
        mediaPlayer.stop();

    }

    // Método que reinicia o jogo
    public void reiniciarJogo(){

        // método que recria a Activity
        this.recreate();

    }

    // Método para mudar a imagem
    public void mudarImagem(){

        // Verifica quantas chances tem e muda as imagens de acordo
        if (chancesRestantes == 6) {

            // drawable recebe a caminho da imagem
            drawable = getResources().getDrawable(R.drawable.forca0);

        } else if (chancesRestantes == 5){

            drawable = getResources().getDrawable(R.drawable.forca1);

        } else if (chancesRestantes == 4){

            drawable = getResources().getDrawable(R.drawable.forca2);

        } else if (chancesRestantes == 3){

            drawable = getResources().getDrawable(R.drawable.forca3);

        } else if (chancesRestantes == 2){

            drawable = getResources().getDrawable(R.drawable.forca4);

        } else if (chancesRestantes == 1){

            drawable = getResources().getDrawable(R.drawable.forca5);

        } else if (chancesRestantes == 0) {

            drawable = getResources().getDrawable(R.drawable.forca6);

        }

        // Exibe a imagem recebendo o drawable com caminho
        imgForca.setImageDrawable(drawable);
    }
}
