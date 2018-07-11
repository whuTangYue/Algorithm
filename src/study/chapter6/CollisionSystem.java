package study.chapter6;

import java.awt.Color;

import depend.StdDraw;
import depend.StdRandom;
import study.chapter2.MinPQ;

public class CollisionSystem {
	private static final double HZ = 0.5; // number of redraw events per clock tick

	/***************************************************************************
	 * An event during a particle collision simulation. Each event contains the time
	 * at which it will occur (assuming no supervening actions) and the particles a
	 * and b involved.
	 *
	 * - a and b both null: redraw event - a null, b not null: collision with
	 * vertical wall - a not null, b null: collision with horizontal wall - a and b
	 * both not null: binary collision between a and b
	 *
	 ***************************************************************************/
	private class Event implements Comparable<Event> {
		private final double time;
		private final Particle a, b;
		private final int countA, countB;

		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if (a != null)
				countA = a.count();
			else
				countA = -1;
			if (b != null)
				countB = b.count();
			else
				countB = -1;
		}

		public int compareTo(Event that) {
			if (this.time > that.time)
				return +1;
			else if (this.time < that.time)
				return -1;
			else
				return 0;
		}

		public boolean isValid() {
			if (a != null && a.count() != countA)
				return false;
			if (b != null && b.count() != countB)
				return false;
			return true;
		}

	}

	private static class Particle {
		private static final double INFINITY = Double.POSITIVE_INFINITY;

		private double rx, ry; // position
		private double vx, vy; // velocity
		private int count; // number of collisions so far
		private final double radius; // radius
		private final double mass; // mass
		private final Color color; // color

		/**
		 * Initializes a particle with the specified position, velocity, radius, mass,
		 * and color.
		 *
		 * @param rx
		 *            <em>x</em>-coordinate of position
		 * @param ry
		 *            <em>y</em>-coordinate of position
		 * @param vx
		 *            <em>x</em>-coordinate of velocity
		 * @param vy
		 *            <em>y</em>-coordinate of velocity
		 * @param radius
		 *            the radius
		 * @param mass
		 *            the mass
		 * @param color
		 *            the color
		 */
		@SuppressWarnings("unused")
		public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
			this.vx = vx;
			this.vy = vy;
			this.rx = rx;
			this.ry = ry;
			this.radius = radius;
			this.mass = mass;
			this.color = color;
		}

		/**
		 * Initializes a particle with a random position and velocity. The position is
		 * uniform in the unit box; the velocity in either direciton is chosen uniformly
		 * at random.
		 */
		public Particle() {
			rx = StdRandom.uniform(0.0, 1.0);
			ry = StdRandom.uniform(0.0, 1.0);
			vx = StdRandom.uniform(-0.005, 0.005);
			vy = StdRandom.uniform(-0.005, 0.005);
			radius = 0.01;
			mass = 0.5;
			color = Color.BLACK;
		}

		/**
		 * Moves this particle in a straight line (based on its velocity) for the
		 * specified amount of time.
		 *
		 * @param dt
		 *            the amount of time
		 */
		public void move(double dt) {
			rx += vx * dt;
			ry += vy * dt;
		}

		/**
		 * Draws this particle to standard draw.
		 */
		public void draw() {
			StdDraw.setPenColor(color);
			StdDraw.filledCircle(rx, ry, radius);
		}

		/**
		 * Returns the number of collisions involving this particle with vertical walls,
		 * horizontal walls, or other particles. This is equal to the number of calls to
		 * {@link #bounceOff}, {@link #bounceOffVerticalWall}, and
		 * {@link #bounceOffHorizontalWall}.
		 *
		 * @return the number of collisions involving this particle with vertical walls,
		 *         horizontal walls, or other particles
		 */
		public int count() {
			return count;
		}

		/**
		 * Returns the amount of time for this particle to collide with the specified
		 * particle, assuming no interening collisions.
		 *
		 * @param that
		 *            the other particle
		 * @return the amount of time for this particle to collide with the specified
		 *         particle, assuming no interening collisions;
		 *         {@code Double.POSITIVE_INFINITY} if the particles will not collide
		 */
		public double timeToHit(Particle that) {
			if (this == that)
				return INFINITY;
			double dx = that.rx - this.rx;
			double dy = that.ry - this.ry;
			double dvx = that.vx - this.vx;
			double dvy = that.vy - this.vy;
			double dvdr = dx * dvx + dy * dvy;
			if (dvdr > 0)
				return INFINITY;
			double dvdv = dvx * dvx + dvy * dvy;
			if (dvdv == 0)
				return INFINITY;
			double drdr = dx * dx + dy * dy;
			double sigma = this.radius + that.radius;
			double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
			// if (drdr < sigma*sigma) StdOut.println("overlapping particles");
			if (d < 0)
				return INFINITY;
			return -(dvdr + Math.sqrt(d)) / dvdv;
		}

		/**
		 * Returns the amount of time for this particle to collide with a vertical wall,
		 * assuming no interening collisions.
		 *
		 * @return the amount of time for this particle to collide with a vertical wall,
		 *         assuming no interening collisions; {@code Double.POSITIVE_INFINITY}
		 *         if the particle will not collide with a vertical wall
		 */
		public double timeToHitVerticalWall() {
			if (vx > 0)
				return (1.0 - rx - radius) / vx;
			else if (vx < 0)
				return (radius - rx) / vx;
			else
				return INFINITY;
		}

		/**
		 * Returns the amount of time for this particle to collide with a horizontal
		 * wall, assuming no interening collisions.
		 *
		 * @return the amount of time for this particle to collide with a horizontal
		 *         wall, assuming no interening collisions;
		 *         {@code Double.POSITIVE_INFINITY} if the particle will not collide
		 *         with a horizontal wall
		 */
		public double timeToHitHorizontalWall() {
			if (vy > 0)
				return (1.0 - ry - radius) / vy;
			else if (vy < 0)
				return (radius - ry) / vy;
			else
				return INFINITY;
		}

		/**
		 * Updates the velocities of this particle and the specified particle according
		 * to the laws of elastic collision. Assumes that the particles are colliding at
		 * this instant.
		 *
		 * @param that
		 *            the other particle
		 */
		public void bounceOff(Particle that) {
			double dx = that.rx - this.rx;
			double dy = that.ry - this.ry;
			double dvx = that.vx - this.vx;
			double dvy = that.vy - this.vy;
			double dvdr = dx * dvx + dy * dvy; // dv dot dr
			double dist = this.radius + that.radius; // distance between particle centers at collison

			// magnitude of normal force
			double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

			// normal force, and in x and y directions
			double fx = magnitude * dx / dist;
			double fy = magnitude * dy / dist;

			// update velocities according to normal force
			this.vx += fx / this.mass;
			this.vy += fy / this.mass;
			that.vx -= fx / that.mass;
			that.vy -= fy / that.mass;

			// update collision counts
			this.count++;
			that.count++;
		}

		/**
		 * Updates the velocity of this particle upon collision with a vertical wall (by
		 * reflecting the velocity in the <em>x</em>-direction). Assumes that the
		 * particle is colliding with a vertical wall at this instant.
		 */
		public void bounceOffVerticalWall() {
			vx = -vx;
			count++;
		}

		/**
		 * Updates the velocity of this particle upon collision with a horizontal wall
		 * (by reflecting the velocity in the <em>y</em>-direction). Assumes that the
		 * particle is colliding with a horizontal wall at this instant.
		 */
		public void bounceOffHorizontalWall() {
			vy = -vy;
			count++;
		}

		/**
		 * Returns the kinetic energy of this particle. The kinetic energy is given by
		 * the formula 1/2 <em>m</em> <em>v</em><sup>2</sup>, where <em>m</em> is the
		 * mass of this particle and <em>v</em> is its velocity.
		 *
		 * @return the kinetic energy of this particle
		 */
		@SuppressWarnings("unused")
		public double kineticEnergy() {
			return 0.5 * mass * (vx * vx + vy * vy);
		}
	}

	private MinPQ<Event> pq;
	private double t = 0;
	private Particle[] particles;

	public CollisionSystem(Particle[] particles) {
		this.particles = particles.clone();
	}

	private void predictCollisions(Particle a, double limit) {
		if (a == null)
			return;
		for (int i = 0; i < particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			if (t + dt < limit) {
				pq.add(new Event(t + dt, a, particles[i]));
			}
		}
		double dtX = a.timeToHitVerticalWall();
		double dtY = a.timeToHitHorizontalWall();
		if (t + dtX < limit) {
			pq.add(new Event(t + dtX, a, null));
		}
		if (t + dtY < limit) {
			pq.add(new Event(t + dtY, null, a));
		}
	}

	public void redraw(double limit) {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(20);
		if (t < limit) {
			pq.add(new Event(t + 1.0 / HZ, null, null));
		}
	}

	public void simulate(double limit) {
		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++) {
			predictCollisions(particles[i], limit);
		}
		pq.add(new Event(0, null, null)); // redraw event
		// the main event-driven simulation loop
		while (!pq.isEmpty()) {

			// get impending event, discard if invalidated
			Event e = pq.delMin();
			if (!e.isValid())
				continue;
			Particle a = e.a;
			Particle b = e.b;

			// physical collision, so update positions, and then simulation clock
			for (int i = 0; i < particles.length; i++)
				particles[i].move(e.time - t);
			t = e.time;

			// process event
			if (a != null && b != null)
				a.bounceOff(b); // particle-particle collision
			else if (a != null && b == null)
				a.bounceOffVerticalWall(); // particle-wall collision
			else if (a == null && b != null)
				b.bounceOffHorizontalWall(); // particle-wall collision
			else if (a == null && b == null)
				redraw(limit); // redraw event

			// update the priority queue with new collisions involving a or b
			predictCollisions(a, limit);
			predictCollisions(b, limit);
		}
	}

	public static void main(String[] args) {

		StdDraw.setCanvasSize(800, 800);

		// remove the border
		// StdDraw.setXscale(1.0/22.0, 21.0/22.0);
		// StdDraw.setYscale(1.0/22.0, 21.0/22.0);

		// enable double buffering
		StdDraw.enableDoubleBuffering();

		// the array of particles
		Particle[] particles;

		// create n random particles

		int n = 50;
		particles = new Particle[n];
		for (int i = 0; i < n; i++)
			particles[i] = new CollisionSystem.Particle();

		// create collision system and simulate
		CollisionSystem system = new CollisionSystem(particles);
		system.simulate(10000);
	}
}
