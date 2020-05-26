<?php


namespace AffectationBundle\Controller;

use AffectationBundle\Entity\Affectation;
use AffectationBundle\Repository\AffectationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Validator\Constraints\DateTime;

class ServiceAffectationController extends Controller
{
    public function readAction(Request $request, $tag)
    {
        //on va recuperer les affectations dans ce tableaux d objets
        $affectations = new Affectation();

        if($tag == 'all')
        {
            $em = $this->getDoctrine()->getManager();

            $affectations = $em->getRepository('AffectationBundle:Affectation')->findAll();

        }

        else {

            $affectations = $this->getDoctrine()
                ->getRepository(Affectation::class)
                ->findAffectation($tag);

        }

        //definir un objet serialiser pour normaliser notre tableaux d'objet
        $serializer = new Serializer([new ObjectNormalizer()]);


        //la normalisation retourne un tableaux des strings (formatted) pret pour etre encoder en format json
        $formatted = $serializer->normalize($affectations);

        //encoder le tableaux normalisee (formatted) en format json et lui retourner comme retour de cette methode
        return new JsonResponse($formatted);
    }

    public function newAction(Request $request)
    {
        //initialiser un objet de type affectation
        $affectation = new Affectation();

        //rensegnier les attribut de cet objet a partir de l url
        $affectation->setRemarque($request->get('remarque'));
        $affectation->setDate(\DateTime::createFromFormat('Y-m-d', $request->get('date')));

        //persister l objet sur la bdd
        $em = $this->getDoctrine()->getManager();
        $em->persist($affectation);
        $em->flush();

        //definir un objet serialiser pour normaliser notre objet (affectation)
        $serializer = new Serializer([new ObjectNormalizer()]);

        //la normalisation retourne un tableaux des strings (formatted) pret pour etre encoder en format json
        $formatted = $serializer->normalize($affectation);

        //encoder le tableaux normalisee (formatted) en format json et lui retourner comme retour de cette methode
        return new JsonResponse($formatted);
    }

    public function editAction(Request $request, Affectation $id)
    {
        //recuperer l objet selon l id passee en paramettre dans l url
        $affectation=$this->getDoctrine()->getRepository(Affectation::class)->find($id);

        //rensegnier les attribut de cet objet a partir de l url
        $affectation->setRemarque($request->get('remarque'));
        $affectation->setDate(\DateTime::createFromFormat('Y-m-d', $request->get('date')));

        //persister l objet sur la bdd
        $em = $this->getDoctrine()->getManager();
        $em->persist($affectation);
        $em->flush();

        //definir un objet serialiser pour normaliser notre objet (affectation)
        $serializer = new Serializer([new ObjectNormalizer()]);

        //la normalisation retourne un tableaux des strings (formatted) pret pour etre encoder en format json
        $formatted = $serializer->normalize($affectation);

        //encoder le tableaux normalisee (formatted) en format json et lui retourner comme retour de cette methode
        return new JsonResponse($formatted);

    }

    public function deleteAction(Request $request, Affectation $id)
    {
        //recuperer l objet selon l id passee en paramettre dans l url
        $affectation=$this->getDoctrine()->getRepository(Affectation::class)->find($id);

        //definir un objet serialiser pour normaliser notre objet (affectation)
        $serializer = new Serializer([new ObjectNormalizer()]);

        //la normalisation retourne un tableaux des strings (formatted) pret pour etre encoder en format json
        $formatted = $serializer->normalize($affectation);

        //suppimer l objet de la bdd
        $em = $this->getDoctrine()->getManager();
        $em->remove($affectation);
        $em->flush();

        //encoder le tableaux normalisee (formatted) en format json et lui retourner comme retour de cette methode
        return new JsonResponse($formatted);

    }


}