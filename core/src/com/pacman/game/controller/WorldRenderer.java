package com.pacman.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.model.Ghost;
import com.pacman.game.model.Barriere;
import com.pacman.game.model.Ghost1;
import com.pacman.game.model.Ghost2;
import com.pacman.game.model.Intersection;
import com.pacman.game.model.IntersectionPellet;
import com.pacman.game.model.Maison;
import com.pacman.game.model.Pacman;
import com.pacman.game.model.Pellet;
import com.pacman.game.model.Super;
import com.pacman.game.model.Vide;
import com.pacman.game.model.Block;
import com.pacman.game.model.World;
import com.pacman.game.model.GameElement;
import com.pacman.game.screen.EndScreen;
import com.pacman.game.screen.GameScreen;
import com.pacman.game.view.TextureFactory;
import com.pacman.game.view.TextureGhost1;
import com.pacman.game.view.TextureGhost2;
import com.pacman.game.view.TexturePacman;
import com.pacman.game.view.TextureSuper;

public class WorldRenderer {

    private World world;
    private Game game;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private float ppuX, ppuY;
    private int score;
    private float time;
    private float compteurInvincibilite;
    long startTime;
    long globalEscapeTime;
    private boolean barrieres;





    /*****PARAMETRES DE JEU*****/
    private int invincibiliteTime = 5; //en secondes : temps d'invicibilité de Pacman après avoir mangé une super pac-gomme
    private int barriereTime = 5; //en secondes : temps avec que la barrière disparaisse


    public WorldRenderer(World world, Game game) {
        this.world = world;
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        this.score=0;
        startTime = System.currentTimeMillis();
        globalEscapeTime = System.nanoTime();
        this.barrieres=true;

    }

    public void render(float delta) {
        deplacement();
        animation(delta);
        doitSortir(delta);

        Vector2 pos=this.world.getPacman().getPosition();
        if(pos.x%1==0&&pos.y%1==0) {
            GameElement ge=this.world.getMaze().get((int)pos.x, (int)pos.y);
            if(ge instanceof Super || ge instanceof IntersectionPellet) {
                if(ge instanceof Super) {
                    invincibilite();
                }
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Intersection(new Vector2((int)pos.x,(int)pos.y),this.world));
                this.score+=10;
            }
            else if(ge instanceof Pellet) {
                this.world.getMaze().set((int)pos.x, (int)pos.y,new Vide(new Vector2((int)pos.x,(int)pos.y),this.world));
                this.score+=10;
            }
        }


        if(this.world.getGhost1().getEscape()|| this.world.getGhost2().getEscape() || this.world.getGhost3().getEscape() || this.world.getGhost4().getEscape()) {
            checkInvincibiliteTime(delta);


            if (this.world.getPacman().getRectangle().overlaps(this.world.getGhost1().getRectangle())) {
                this.world.getGhost1().setEscape(false);
                this.world.getGhost1().setDead(true);
            }
            if (this.world.getPacman().getRectangle().overlaps(this.world.getGhost2().getRectangle())) {
                this.world.getGhost2().setEscape(false);
                this.world.getGhost2().setDead(true);
            }
            if (this.world.getPacman().getRectangle().overlaps(this.world.getGhost3().getRectangle())) {
                this.world.getGhost3().setEscape(false);
                this.world.getGhost3().setDead(true);
            }
            if (this.world.getPacman().getRectangle().overlaps(this.world.getGhost4().getRectangle())) {
                this.world.getGhost4().setEscape(false);
                this.world.getGhost4().setDead(true);
            }
        }


        this.spriteBatch.begin();
        for (GameElement element : this.world) {
            this.spriteBatch.draw(
                    TextureFactory.getInstance(world).getTexture(element.getClass()),
                    element.getPosition().x * ppuX,
                    element.getPosition().y * ppuY,
                    element.getWidth() * ppuX,
                    element.getHeight() * ppuY
            );
        }

        teleportation();
        setPanel();
        checkGameOver();

        this.spriteBatch.end();
    }

    private void teleportation(){
           if (this.world.getME().getRectangle().overlaps(new Rectangle(3,1,1,1))){
            System.out.println("fsfsdf");
        }
    }


    /*****Methoqe qui initialise les déplacements de chaque MoveableElement*****/
    private void deplacement(){
        this.world.getPacman().deplacement();
        this.world.getGhost1().deplacement();
        this.world.getGhost2().deplacement();
        this.world.getGhost3().deplacement();
        this.world.getGhost4().deplacement();
    }

    /*****Methoqe qui initialise les animations*****/
    private void animation(float delta){
        TexturePacman texturePacman = (TexturePacman) TextureFactory.getInstance(world).getTexturable(Pacman.class);
        texturePacman.render(delta);
    }

    private boolean barrieres(float deltaTime){
        time += deltaTime;
        if(time >= barriereTime && barrieres){
            barrieres=false;
            return true;
        }
        return false;
    }

    private void checkInvincibiliteTime(float deltaTime){
        compteurInvincibilite += deltaTime;
        if(compteurInvincibilite >= invincibiliteTime){
            this.world.getGhost1().setEscape(false);
            this.world.getGhost2().setEscape(false);
            this.world.getGhost3().setEscape(false);
            this.world.getGhost4().setEscape(false);
        }
    }

    /*****Méthode qui passe les fantômes en mode fuite si Pacman a mangé une super pac-gomme*****/
    private void invincibilite(){
        this.world.getGhost1().setEscape(true);
        this.world.getGhost2().setEscape(true);
        this.world.getGhost3().setEscape(true);
        this.world.getGhost4().setEscape(true);
        compteurInvincibilite = 0;

        }


    /*****Methode qui force les fantômes à sortir de la maison si la barrière est ouverte*****/
    private void doitSortir(float delta){
        if(barrieres(delta)){
            this.world.getMaze().enleveBarriere();
            this.world.getGhost1().setDoitSortir(true);
            this.world.getGhost2().setDoitSortir(true);
            this.world.getGhost3().setDoitSortir(true);
            this.world.getGhost4().setDoitSortir(true);
        }
    }

    /****Methode affichant le panel de score et de temps*****/
    private void setPanel(){
        font.draw(spriteBatch,"Score:"+ Integer.toString(score), 0, (this.world.getHeight()+1)*ppuY);
        font.draw(spriteBatch,"Temps écoulé = " + (int)time +'s', (this.world.getWidth()-7)*ppuX, (this.world.getHeight()+1)*ppuY);
    }

    /*****Methode qui amène vers le Endscreen si Pacman touche un fantôme dont l'état n'est ni Escape ni Dead.*****/
    private void checkGameOver(){
        if((this.world.getPacman().getRectangle().overlaps(this.world.getGhost2().getRectangle()) && !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost1().getRectangle())&& !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost3().getRectangle())&& !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost4().getRectangle())&& !this.world.getGhost1().getEscape())){
            game.setScreen(new EndScreen(game, score, (long)time, world, ppuX, ppuY));
        }
    }

    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}