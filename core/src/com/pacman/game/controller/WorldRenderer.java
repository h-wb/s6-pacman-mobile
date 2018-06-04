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
import com.pacman.game.model.Ghost3;
import com.pacman.game.model.Ghost4;
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
import com.pacman.game.view.TextureGhost3;
import com.pacman.game.view.TextureGhost4;
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
    private boolean barrieres;
    private float timeGhost1 = 0, timeGhost2 = 0, timeGhost3 = 0, timeGhost4 = 0;


    /*****PARAMETRES DE JEU*****/
    private int invincibiliteTime = 5; //en secondes : temps d'invicibilité de Pacman après avoir mangé une super pac-gomme
    private int barriereTime = 5; //en secondes : temps avec que la barrière disparaisse
    private float fantomeEscapeVitesse = 0.1f;
    private float fantomeMortVitesse = 0.2f;
    private float fantomeVitesse = 0.1f;
    private int clignoteTime = 2;
    private float waitTime = 4;


    public WorldRenderer(World world, Game game) {
        this.world = world;
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        this.score = 0;
        this.barrieres = true;

    }

    public void render(float delta) {
        regultaionDesFantomes();
        if (!barrieres)
            checkWaitTime(delta);
        teleportation();
        deplacement();
        animation(delta);
        doitSortir(delta);
        checkGameOver();
        score();
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
        setPanel();
        checkInvincibiliteTime(delta);
        this.spriteBatch.end();
    }

    /*****Methode qui téléporte les MoveableElement (redondant, accéder à superclasse MoveableElement commment?)*****/
    private void teleportation() {
        int limiteN = 27;
        int limiteS = 0;
        Vector2 tpN = new Vector2(14, 26.9f);
        Vector2 tpS = new Vector2(14, 0.1f);
        if (this.world.getPacman().getPosition().y == limiteN) {
            this.world.getPacman().setPosition(tpS);
            world.getMaze().mange(world.getMaze().get((int) tpS.x, limiteS));
        } else if (this.world.getPacman().getPosition().y == limiteS) {
            this.world.getPacman().setPosition(tpN);
            world.getMaze().mange(world.getMaze().get((int) tpN.x, limiteN));
        }
        if (this.world.getGhost1().getPosition().y == limiteN) {
            this.world.getGhost1().setPosition(tpS);
        } else if (this.world.getGhost1().getPosition().y == limiteS) {
            this.world.getGhost1().setPosition(tpN);
        }
        if (this.world.getGhost2().getPosition().y == limiteN) {
            this.world.getGhost2().setPosition(tpS);
        } else if (this.world.getGhost2().getPosition().y == limiteS) {
            this.world.getGhost2().setPosition(tpN);
        }
        if (this.world.getGhost3().getPosition().y == limiteN) {
            this.world.getGhost3().setPosition(tpS);
        } else if (this.world.getGhost3().getPosition().y == limiteS) {
            this.world.getGhost3().setPosition(tpN);
        }
        if (this.world.getGhost4().getPosition().y == limiteN) {
            this.world.getGhost4().setPosition(tpS);
        } else if (this.world.getGhost4().getPosition().y == limiteS) {
            this.world.getGhost4().setPosition(tpN);
        }

    }

    /*****Methode qui met à jour le score si pac-gomme, et appelle invicible si super pac-gomme *****/
    private void score() {
        Vector2 pos = this.world.getPacman().getPosition();
        if (pos.x % 1 == 0 && pos.y % 1 == 0) {
            GameElement ge = this.world.getMaze().get((int) pos.x, (int) pos.y);
            if (ge instanceof IntersectionPellet || ge instanceof Pellet) {
                if (ge instanceof Super) {
                    invincibilite();
                }
                world.getMaze().mange(ge);
                this.score += 10;
            }
        }
    }

    /*****Methode qui initialise les déplacements de chaque MoveableElement*****/
    private void deplacement() {
        this.world.getPacman().deplacement();
        this.world.getGhost1().deplacement();
        this.world.getGhost2().deplacement();
        this.world.getGhost3().deplacement();
        this.world.getGhost4().deplacement();
    }

    /*****Methode qui initialise les animations*****/
    private void animation(float delta) {
        TexturePacman texturePacman = (TexturePacman) TextureFactory.getInstance(world).getTexturable(Pacman.class);
        texturePacman.render(delta);

        TextureGhost1 textureGhost1 = (TextureGhost1) TextureFactory.getInstance(world).getTexturable(Ghost1.class);
        textureGhost1.render(delta);

        TextureGhost2 textureGhost2 = (TextureGhost2) TextureFactory.getInstance(world).getTexturable(Ghost2.class);
        textureGhost2.render(delta);

        TextureGhost3 textureGhost3 = (TextureGhost3) TextureFactory.getInstance(world).getTexturable(Ghost3.class);
        textureGhost3.render(delta);

        TextureGhost4 textureGhost4 = (TextureGhost4) TextureFactory.getInstance(world).getTexturable(Ghost4.class);
        textureGhost4.render(delta);
    }

    /*****Methode de compteur pour la barriere****/
    private boolean barrieres(float deltaTime) {
        time += deltaTime;
        if (time >= barriereTime && barrieres) {
            barrieres = false;
            return true;
        }
        return false;
    }

    public void regulationFantome(Ghost ghost) {
        if (ghost.getDead()) {
            ghost.setVitesse(fantomeMortVitesse);
        } else if (ghost.getEscape()) {
            ghost.setVitesse(fantomeEscapeVitesse);
        } else {
            ghost.setVitesse(fantomeVitesse);
        }
    }

    public void regultaionDesFantomes() {
        regulationFantome(this.world.getGhost1());
        regulationFantome(this.world.getGhost2());
        regulationFantome(this.world.getGhost3());
        regulationFantome(this.world.getGhost4());
    }


    private void checkWaitTime(float deltaTime) {
        Vector2 pos = this.world.getGhost1().getPosition();
        GameElement geGhost1 = this.world.getMaze().get((int) pos.x, (int) pos.y);
        pos = this.world.getGhost2().getPosition();
        GameElement geGhost2 = this.world.getMaze().get((int) pos.x, (int) pos.y);
        pos = this.world.getGhost3().getPosition();
        GameElement geGhost3 = this.world.getMaze().get((int) pos.x, (int) pos.y);
        pos = this.world.getGhost4().getPosition();
        GameElement geGhost4 = this.world.getMaze().get((int) pos.x, (int) pos.y);

        if (!this.world.getGhost1().isDoitSortir())
            timeGhost1 = timeGhost1 + deltaTime;
        if (!this.world.getGhost2().isDoitSortir())
            timeGhost2 = timeGhost2 + deltaTime;
        if (!this.world.getGhost3().isDoitSortir())
            timeGhost3 = timeGhost3 + deltaTime;
        if (!this.world.getGhost4().isDoitSortir())
            timeGhost4 = timeGhost4 + deltaTime;

        if (geGhost1 instanceof Maison && timeGhost1 >= waitTime) {
            timeGhost1 = 0;
            this.world.getGhost1().setDoitSortir(true);
        }

        if (geGhost2 instanceof Maison && timeGhost2 >= waitTime) {
            timeGhost2 = 0;
            this.world.getGhost2().setDoitSortir(true);
        }

        if (geGhost3 instanceof Maison && timeGhost3 >= waitTime) {
            timeGhost3 = 0;
            this.world.getGhost3().setDoitSortir(true);
        }
        if (geGhost4 instanceof Maison && timeGhost4 >= waitTime) {
            timeGhost4 = 0;
            this.world.getGhost4().setDoitSortir(true);
        }


    }

    /*****Methode compteur invicibilité
     Si les fantômes sont en mode fuite et que le temps est écoulé alors ils repassent en mode normal
     Si ils sont tués pendant le mode fuite, ils retournent à la maison
     (trop redondant encore)*****/
    private void checkInvincibiliteTime(float deltaTime) {
        compteurInvincibilite += deltaTime;
        if (this.world.getGhost1().getEscape() || this.world.getGhost2().getEscape() || this.world.getGhost3().getEscape() || this.world.getGhost4().getEscape()) {
            if (compteurInvincibilite >= invincibiliteTime) {
                this.world.getGhost1().setEscape(false);
                this.world.getGhost2().setEscape(false);
                this.world.getGhost3().setEscape(false);
                this.world.getGhost4().setEscape(false);
            } else if (compteurInvincibilite <= invincibiliteTime) {
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
        }
        if (invincibiliteTime - compteurInvincibilite <= clignoteTime) {
            this.world.getGhost1().setClignote(true);
            this.world.getGhost2().setClignote(true);
            this.world.getGhost3().setClignote(true);
            this.world.getGhost4().setClignote(true);
        } else {
            this.world.getGhost1().setClignote(false);
            this.world.getGhost2().setClignote(false);
            this.world.getGhost3().setClignote(false);
            this.world.getGhost4().setClignote(false);
        }
    }

    /*****Méthode qui passe les fantômes en mode fuite si Pacman a mangé une super pac-gomme*****/
    private void invincibilite() {
        this.world.getGhost1().setEscape(true);
        this.world.getGhost2().setEscape(true);
        this.world.getGhost3().setEscape(true);
        this.world.getGhost4().setEscape(true);
        compteurInvincibilite = 0;

    }


    /*****Methode qui force les fantômes à sortir de la maison si la barrière est ouverte*****/
    private void doitSortir(float delta) {
        if (barrieres(delta)) {
            this.world.getMaze().enleveBarriere();
            this.world.getGhost1().setDoitSortir(true);
            this.world.getGhost2().setDoitSortir(true);
            this.world.getGhost3().setDoitSortir(true);
            this.world.getGhost4().setDoitSortir(true);
        }
    }

    /****Methode affichant le panel de score et de temps*****/
    private void setPanel() {
        font.draw(spriteBatch, "Score:" + Integer.toString(score), 0, (this.world.getHeight() + 1) * ppuY);
        font.draw(spriteBatch, "Temps écoulé = " + (int) time + 's', (this.world.getWidth() - 7) * ppuX, (this.world.getHeight() + 1) * ppuY);
    }

    /*****Methode qui amène vers le Endscreen si Pacman touche un fantôme dont l'état n'est ni Escape ni Dead.*****/
    private void checkGameOver() {
        if ((this.world.getPacman().getRectangle().overlaps(this.world.getGhost2().getRectangle()) && !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost1().getRectangle()) && !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost3().getRectangle()) && !this.world.getGhost1().getEscape()) || (this.world.getPacman().getRectangle().overlaps(this.world.getGhost4().getRectangle()) && !this.world.getGhost1().getEscape())) {
            game.setScreen(new EndScreen(game, score, (long) time, world, ppuX, ppuY));
        }
    }

    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}